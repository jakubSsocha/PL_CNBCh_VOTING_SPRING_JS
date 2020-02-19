package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.uw.cnbch.voting.errors.types.LoadFromDatabaseException;
import pl.edu.uw.cnbch.voting.errors.types.VotingClosedException;
import pl.edu.uw.cnbch.voting.errors.types.VotingInactiveException;
import pl.edu.uw.cnbch.voting.errors.types.VotingNameNotUniqueException;
import pl.edu.uw.cnbch.voting.models.entities.Result;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.entities.Voting;
import pl.edu.uw.cnbch.voting.models.viewDTO.AllVotingDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.VotingDetailsDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.VotingResultDTO;
import pl.edu.uw.cnbch.voting.repositories.VotingRepository;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.ResultService;
import pl.edu.uw.cnbch.voting.services.VotingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VotingServiceImpl implements VotingService {

    private final VotingRepository votingRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final MainService mainService;
    private final ResultService resultService;

    public VotingServiceImpl(VotingRepository votingRepository,
                             BCryptPasswordEncoder passwordEncoder,
                             MainService mainService,
                             ResultService resultService) {
        this.votingRepository = votingRepository;
        this.passwordEncoder = passwordEncoder;
        this.mainService = mainService;
        this.resultService = resultService;
    }

    @Override
    public void create(Voting voting) {
        ifDoesntExistSaveInDatabase(newVoting(voting));
    }

    private Voting newVoting(Voting voting) {
        LocalDateTime now = LocalDateTime.now();
        voting.setCreatedDate(now);
        voting.setActive(true);
        voting.setClosed(false);
        return voting;
    }

    private void ifDoesntExistSaveInDatabase(Voting voting) {
        if (checkIfExist(voting.getName())) {
            throw new VotingNameNotUniqueException();
        } else {
            saveInDatabase(voting);
        }
    }

    @Override
    public List<AllVotingDTO> getAllVotingIdData() {
        List<AllVotingDTO> allVotingDTO = new ArrayList<>();
        for (Voting v : getAllActiveVotingData()) {
            allVotingDTO.add(new AllVotingDTO(v));
        }
        return allVotingDTO;
    }

    @Override
    public void edit(Voting voting) {
        Voting oldSettings = readByName(voting.getName());
        boolean wasSecret = oldSettings.isSecret();
        voting.setCreatedDate(oldSettings.getCreatedDate());
        voting.setLastModificationDate(LocalDateTime.now());
        saveInDatabase(voting);
        if (!wasSecret && voting.isSecret()) {
            resultService.encodeAllResultsForVotingId(voting.getId());
        }
        resultService.setAllResultsInactiveForVotingId(voting.getId());
        resultService.createActiveResultsForAllUsersFor(voting);
    }

    @Override
    public boolean checkIfClosed(Long id) {
        if (getVotingBy(id).isClosed()) {
            throw new VotingClosedException();
        }
        return false;
    }

    @Override
    public boolean checkIfActive(Long id) {
        if (!getVotingBy(id).isActive()) {
            throw new VotingInactiveException();
        }
        return true;
    }

    @Override
    public void delete(Voting voting) {
        Voting oldSettings = readByName(voting.getName());
        voting.setCreatedDate(oldSettings.getCreatedDate());
        voting.setLastModificationDate(LocalDateTime.now());
        voting.setUsers(oldSettings.getUsers());
        checkIfClosed(voting.getId());
        resultService.setAllResultsInactiveForVotingId(voting.getId());
        voting.setActive(false);
        saveInDatabase(voting);
    }

    @Override
    public void close(Voting voting) {
        checkIfClosed(voting.getId());
        Voting oldSettings = readByName(voting.getName());
        LocalDateTime now = LocalDateTime.now();
        resultService.selAllResultsClosedForVotingId(voting.getId(), now);
        voting.setCreatedDate(oldSettings.getCreatedDate());
        voting.setLastModificationDate(now);
        voting.setUsers(oldSettings.getUsers());
        voting.setClosedDate(now);
        voting.setClosed(true);
        saveInDatabase(voting);
    }

    @Override
    public VotingResultDTO generateResultForVoting(Long id) {
        int[] result = countResults(getVotingBy(id));
        return new VotingResultDTO(
                result[0],
                result[1],
                result[2],
                resultService.getAllUsersResultsForVoting(id)
        );
    }

    private int[] countResults(Voting voting) {
        int[] result = new int[3];
        if (voting.isSecret()) {
            for (Result r : resultService.getAllUsersResultsForVoting(voting.getId())) {
                if (r.getVote() == null) {
                    continue;
                } else if (passwordEncoder.matches("ZA", r.getVote())) {
                    result[0] += 1;
                } else if (passwordEncoder.matches("PRZECIW", r.getVote())) {
                    result[1] += 1;
                } else if (passwordEncoder.matches("WSTRZYMUJĘ SIĘ", r.getVote())) {
                    result[2] += 1;
                }
            }
            return result;
        }
        for (Result r : voting.getResults()) {
            if (r.getVote() == null) {
                continue;
            } else if (r.getVote().equals("ZA")) {
                result[0] += 1;
            } else if (r.getVote().equals("PRZECIW")) {
                result[1] += 1;
            } else if (r.getVote().equals("WSTRZYMUJĘ SIĘ")) {
                result[2] += 1;
            }
        }
        return result;
    }

    @Override
    public VotingDetailsDTO getDetailsForVoting(Long id) {
        return new VotingDetailsDTO(getVotingBy(id));
    }

// repository methods

    private void saveInDatabase(Voting voting) {
        votingRepository.save(voting);
    }

    @Override
    public Voting getVotingBy(Long id) {
        return votingRepository.findById(id).orElseThrow(
                () -> new LoadFromDatabaseException()
        );
    }

    @Override
    public Voting readByName(String name) {
        return votingRepository.findByName(name).orElseThrow(
                () -> new LoadFromDatabaseException()
        );
    }

    @Override
    public List<Voting> getAllUserClosedVoting() throws Exception {
        return votingRepository.findAllUserActiveClosedVoting(mainService.getLoggedUser());
    }

    private boolean checkIfExist(String name) {
        return votingRepository.findByName(name).isPresent();
    }

    @Override
    public List<Voting> getAllActiveVotingData() {
        return votingRepository.selectAllActiveVotingOrdered();
    }


}
