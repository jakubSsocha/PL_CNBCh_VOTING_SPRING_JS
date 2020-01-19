package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.stereotype.Service;
import pl.edu.uw.cnbch.voting.models.entities.Voting;
import pl.edu.uw.cnbch.voting.repositories.VotingRepository;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.ResultService;
import pl.edu.uw.cnbch.voting.services.VotingService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VotingServiceImpl implements VotingService {

    private final VotingRepository votingRepository;

    private final MainService mainService;
    private final ResultService resultService;

    public VotingServiceImpl(VotingRepository votingRepository, MainService mainService, ResultService resultService) {
        this.votingRepository = votingRepository;
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
        if(votingRepository.findByName(name).isPresent()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Voting> getAllActiveVotingData() {
        return votingRepository.selectAllActiveVotingOrdered();
    }

    @Override
    public void edit(Voting voting) throws Exception {
        Voting oldSettings = readByName(voting.getName());
        voting.setCreatedDate(oldSettings.getCreatedDate());
        voting.setLastModificationDate(LocalDateTime.now());
        votingRepository.save(voting);
        resultService.setAllResultsInactiveForVotingId(voting.getId());
        resultService.createActiveResultsForAllUsersOf(voting);
    }

    @Override
    public boolean checkIfClosed(Long id) throws Exception {
        Voting voting = readById(id);
        if(voting.isClosed()){
            throw new Exception("Głosowanie zamknięte - nie można go modyfikować");
        }
        else return false;
    }

    @Override
    public void delete(Voting voting) throws Exception {
        Voting oldSettings = readByName(voting.getName());
        voting.setCreatedDate(oldSettings.getCreatedDate());
        voting.setLastModificationDate(LocalDateTime.now());
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
        resultService.selAllResultsClosedForVotingId(voting.getId(),now);
        voting.setCreatedDate(oldSettings.getCreatedDate());
        voting.setLastModificationDate(now);
        voting.setClosedDate(now);
        voting.setClosed(true);
        votingRepository.save(voting);
    }
}
