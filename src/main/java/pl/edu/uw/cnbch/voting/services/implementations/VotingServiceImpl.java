package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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
import java.util.Optional;

@Service
public class VotingServiceImpl implements VotingService {

    private final VotingRepository votingRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final MainService mainService;
    private final ResultService resultService;

    public VotingServiceImpl(VotingRepository votingRepository, BCryptPasswordEncoder passwordEncoder, MainService mainService, ResultService resultService) {
        this.votingRepository = votingRepository;
        this.passwordEncoder = passwordEncoder;
        this.mainService = mainService;
        this.resultService = resultService;
    }

    @Override
    public Voting readById(Long id) throws Exception {
        Optional<Voting> voting = votingRepository.findById(id);
        mainService.checkIfIsEmpty(voting);
        return voting.get();
    }

    @Override
    public Voting readByName(String name) throws Exception {
        Optional<Voting> voting = votingRepository.findByName(name);
        mainService.checkIfIsEmpty(voting);
        return voting.get();
    }

    @Override
    public void create(Voting voting) throws Exception {
        ifDoesntExistSaveInDatabase(newVoting(voting));
    }

    private Voting newVoting(Voting voting) {
        LocalDateTime now = LocalDateTime.now();
        voting.setCreatedDate(now);
        voting.setActive(true);
        voting.setClosed(false);
        return voting;
    }

    private void ifDoesntExistSaveInDatabase(Voting voting) throws Exception {
        if (checkIfExist(voting.getName())) {
            throw new Exception("Głosowanie o podanej nazwie już istnieje w bazie danych");
        } else {
            votingRepository.save(voting);
        }
    }

    private boolean checkIfExist(String name) {
        if (votingRepository.findByName(name).isPresent()) {
            return true;
        } else {
            return false;
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
    public List<Voting> getAllActiveVotingData() {
        return votingRepository.selectAllActiveVotingOrdered();
    }

    @Override
    public void edit(Voting voting) throws Exception {
        Voting oldSettings = readByName(voting.getName());
        boolean wasSecret = oldSettings.isSecret();
        voting.setCreatedDate(oldSettings.getCreatedDate());
        voting.setLastModificationDate(LocalDateTime.now());
        votingRepository.save(voting);
        if (!wasSecret && voting.isSecret()) {
            resultService.encodeAllResultsForVotingId(voting.getId());
        }
        resultService.setAllResultsInactiveForVotingId(voting.getId());
        resultService.createActiveResultsForAllUsersOf(voting);
    }

    @Override
    public boolean checkIfClosed(Long id) throws Exception {
        Voting voting = readById(id);
        if (voting.isClosed()) {
            throw new Exception("Głosowanie zamknięte - nie można go modyfikować");
        } else return false;
    }

    @Override
    public boolean checkIfActive(Long id) throws Exception {
        Voting voting = readById(id);
        if (!voting.isActive()) {
            throw new Exception("Głosowanie zostało usunięte - nie można go modyfikować");
        } else return true;
    }

    @Override
    public void delete(Voting voting) throws Exception {
        Voting oldSettings = readByName(voting.getName());
        voting.setCreatedDate(oldSettings.getCreatedDate());
        voting.setLastModificationDate(LocalDateTime.now());
        voting.setUsers(oldSettings.getUsers());
        checkIfClosed(voting.getId());
        resultService.setAllResultsInactiveForVotingId(voting.getId());
        voting.setActive(false);
        votingRepository.save(voting);
    }

    @Override
    public void close(Voting voting) throws Exception {
        checkIfClosed(voting.getId());
        Voting oldSettings = readByName(voting.getName());
        LocalDateTime now = LocalDateTime.now();
        resultService.selAllResultsClosedForVotingId(voting.getId(), now);
        voting.setCreatedDate(oldSettings.getCreatedDate());
        voting.setLastModificationDate(now);
        voting.setUsers(oldSettings.getUsers());
        voting.setClosedDate(now);
        voting.setClosed(true);
        votingRepository.save(voting);
    }

    @Override
    public VotingResultDTO generateResultForVoting(Long id) throws Exception {
        Voting voting = readById(id);
        int[] result = countResults(voting);
        return new VotingResultDTO(
                result[0],
                result[1],
                result[2],
                resultService.getAllUsersResultsForVoting(voting.getId())
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
        } else {
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
    }

    @Override
    public List<Voting> getAllUserClosedVoting() throws Exception {
        User user = mainService.getLoggedUser();
        return votingRepository.findAllUserActiveClosedVoting(user);
    }

    @Override
    public VotingDetailsDTO getDetailsForVoting(Long id) throws Exception {
        Voting voting = readById(id);
        return new VotingDetailsDTO(voting);
    }
}
