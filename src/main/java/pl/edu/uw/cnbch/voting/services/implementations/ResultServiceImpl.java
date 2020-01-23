package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.uw.cnbch.voting.models.entities.Result;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.entities.Voting;
import pl.edu.uw.cnbch.voting.repositories.ResultRepository;
import pl.edu.uw.cnbch.voting.repositories.UserRepository;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.ResultService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MainService mainService;

    public ResultServiceImpl(ResultRepository resultRepository,
                             UserRepository userRepository,
                             BCryptPasswordEncoder passwordEncoder,
                             MainService mainService) {
        this.resultRepository = resultRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mainService = mainService;
    }

    @Override
    public void createActiveResultsForAllUsersOf(Voting voting) {
        LocalDateTime currentTime = LocalDateTime.now();
        for (User user : voting.getUsers()) {
            ifResultDoesntExistsSaveInDatabase(newResultFor(voting, user, currentTime));
        }
    }

    private Result newResultFor(Voting voting, User user, LocalDateTime time) {
        Result result = new Result();
        result.setVoting(voting);
        result.setUser(user);
        result.setActive(true);
        result.setCreatedDate(time);
        result.setClosed(false);
        return result;
    }

    private void ifResultDoesntExistsSaveInDatabase(Result result) {
        Optional<Result> ResultFromDatabase = resultRepository.findByVotingAndUser(result.getVoting(), result.getUser());
        if (ResultFromDatabase.isPresent() && ResultFromDatabase.get().isActive()==false) {
            Result oldResult = ResultFromDatabase.get();
            oldResult.setActive(true);
            result = oldResult;
        } else if(ResultFromDatabase.isPresent()) {
            throw new DataIntegrityViolationException("Rezultat dla podanego głosowania i użytkownika już istniej");
        }
        resultRepository.save(result);
    }

    @Override
    public List<Result> getAllResultsForVotingWith(Long id) {
        return resultRepository.findByVotingId(id);
    }

    @Override
    public Result findById(Long id) throws Exception{
        Optional<Result> result = resultRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        } else {
            throw new Exception("Głosowanie o podanym Id nie istnieje");
        }
    }

    @Override
    public void save (Result result){
        resultRepository.save(result);
    }

    @Override
    public void checkIfResultIsActive(Long id) throws Exception {
        Result result = findById(id);
        if (result.isClosed()) {
            throw new Exception("Nie można oddać głosu");
        }
    }

    @Override
    public void saveUserVoteFor(Result result) throws Exception {
        if(result.getVote() == null){
            throw new Exception("Głos nie może być pusty");
        }
        Result voteResult = getAllGeneralInformationFor(result);
        if(result.getVoting().isSecret()){
            voteResult = encodeResultVote(voteResult);
        }
        voteResult.setClosed(true);
        voteResult.setUserVotedDate(LocalDateTime.now());
        save(voteResult);
    }

    private Result getAllGeneralInformationFor(Result result) throws Exception{
        Result oldResult = findById(result.getId());
        result.setId(oldResult.getId());
        result.setUser(oldResult.getUser());
        result.setVoting(oldResult.getVoting());
        result.setCreatedDate(oldResult.getCreatedDate());
        result.setActive(oldResult.isActive());
        return result;
    }

    @Override
    public void checkIfResultBelongToUser(Long resultId) throws Exception {
        Long userId = mainService.getLoggedUser().getId();
        Optional<Result> result = resultRepository.checkIfResultBelongToUser(resultId, userId);
        if(!result.isPresent()){
            throw new Exception("Odmowa dostępu");
        }
    }

    @Override
    public void setAllResultsInactiveForVotingId(Long id){
        List<Result> resultList = resultRepository.findAllByVotingId(id);
        for(Result r : resultList){
            r.setActive(false);
            resultRepository.save(r);
        }
    }

    @Override
    public void selAllResultsClosedForVotingId(Long id, LocalDateTime now){
        List<Result> resultList = resultRepository.findAllByVotingId(id);
        for(Result r : resultList){
            closeResult(r, now);
        }
    }

    private void closeResult(Result result, LocalDateTime time){
        result.setClosed(true);
        result.setClosedDate(time);
        resultRepository.save(result);
    }

    @Override
    public void encodeAllResultsForVotingId(Long id){
        List<Result> resultList = resultRepository.findAllByVotingId(id);
        for(Result r : resultList){
            r = encodeResultVote(r);
            save(r);
        }
    }

    private Result encodeResultVote(Result result) {
        if (result.getVote() != null) {
            result.setVote(passwordEncoder.encode(result.getVote()));
        }
        return result;
    }

    @Override
    public List<Result> getAllEmptyResultsForUser() throws Exception {
        return resultRepository.findAllEmptyUserResults(mainService.getLoggedUser().getId());
    }

    @Override
    public List<Result> getAllUsersResultsForVoting(Long id) {
        return resultRepository.findAllFinalResultsForVoting(id);
    }

    @Override
    public void setAllUnclosedUserResultInactive(Long id) {
        List<Result> resultList = resultRepository.findAllUserUnclosedResults(id);
        for(Result r: resultList){
            r.setActive(false);
            resultRepository.save(r);
        }
    }

    @Override
    public void setAllUnclosedUserResultActive(Long id) {
        List<Result> resultList = resultRepository.findAllUserUnclosedResults(id);
        for(Result r: resultList){
            r.setActive(true);
            resultRepository.save(r);
        }
    }
}
