package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.uw.cnbch.voting.errors.types.*;
import pl.edu.uw.cnbch.voting.models.entities.Result;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.entities.Voting;
import pl.edu.uw.cnbch.voting.repositories.ResultRepository;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.ResultService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MainService mainService;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository,
                             BCryptPasswordEncoder passwordEncoder,
                             MainService mainService) {
        this.resultRepository = resultRepository;
        this.passwordEncoder = passwordEncoder;
        this.mainService = mainService;
    }

    @Override
    public void createActiveResultsForAllUsersFor(Voting voting) {
        LocalDateTime currentTime = LocalDateTime.now();
        for (User user : voting.getUsers()) {
            ifResultDoesntExistsSaveInDatabase(newResultFor(voting, user, currentTime));
        }
    }

    private void ifResultDoesntExistsSaveInDatabase(Result result) {
        Optional<Result> ResultFromDatabase = findByVotingAndUser(result.getVoting(), result.getUser());
        if(ResultFromDatabase.isPresent()) {
            if (!ResultFromDatabase.get().isActive()) {
                Result oldResult = ResultFromDatabase.get();
                oldResult.setActive(true);
                result = oldResult;
            } else {
                throw new ResultAlreadyExistsException();
            }
        }
        save(result);
    }

    private Result newResultFor(Voting voting, User user, LocalDateTime time) {
        return new Result.Builder()
                .voting(voting)
                .user(user)
                .active(true)
                .createdDate(time)
                .closed(false)
                .build();
    }

    @Override
    public void checkIfResultIsActive(Long id){
        if (findResultById(id).isClosed()) {
            throw new InactiveResultException();
        }
    }

    @Override
    public void saveUserVoteFor(Result result){
        checkIfVoteNotEmptyFor(result);
        Result voteResult = setAllGeneralInformationForExisting(result);
        encodeVoteIfVotingIsSecretFor(voteResult);
        voteResult.setClosed(true);
        voteResult.setUserVotedDate(LocalDateTime.now());
        save(voteResult);
    }

    private void checkIfVoteNotEmptyFor(Result result){
        if(result.getVote() == null){
            throw new EmptyVoiceException();
        }
    }

    private Result encodeVoteIfVotingIsSecretFor(Result result){
        if(result.getVoting().isSecret()){
            encodeVote(result);
        }
        return result;
    }

    private Result setAllGeneralInformationForExisting(Result result){
        Result oldResult = findResultById(result.getId());
        result.setUser(oldResult.getUser());
        result.setVoting(oldResult.getVoting());
        result.setCreatedDate(oldResult.getCreatedDate());
        result.setActive(oldResult.isActive());
        return result;
    }

    @Override
    public void setAllResultsInactiveForVotingId(Long id){
        for(Result result : findAllResultsByVotingId(id)){
            result.setActive(false);
            save(result);
        }
    }

    @Override
    public void selAllResultsClosedForVotingId(Long id, LocalDateTime now){
        for(Result result : findAllResultsByVotingId(id)){
            closeResult(result, now);
        }
    }

    private void closeResult(Result result, LocalDateTime time){
        result.setClosed(true);
        result.setClosedDate(time);
        save(result);
    }

    @Override
    public void encodeAllResultsForVotingId(Long id){
        for(Result r : findAllResultsByVotingId(id)){
            encodeVote(r);
            save(r);
        }
    }

    private Result encodeVote(Result result) {
        if (result.getVote() != null) {
            result.setVote(passwordEncoder.encode(result.getVote()));
        }
        return result;
    }

    @Override
    public void setAllUnclosedUserResultInactive(Long id) {
        for(Result result: findAllUserUnclosedResults(id)){
            result.setActive(false);
            save(result);
        }
    }

    @Override
    public void setAllUnclosedUserResultActive(Long id) {
        for(Result result: findAllUserUnclosedResults(id)){
            result.setActive(true);
            save(result);
        }
    }

// repository methods

    private Optional<Result> findByVotingAndUser(Voting voting, User user){
        return resultRepository.findByVotingAndUser(voting, user);
    }

    private List<Result> findAllResultsByVotingId(Long id){
        return resultRepository.findAllByVotingId(id);
    }

    private List<Result> findAllUserUnclosedResults(Long id){
        return resultRepository.findAllUserUnclosedResults(id);
    }

    private void save (Result result){
        resultRepository.save(result);
    }

    @Override
    public List<Result> getAllUsersResultsForVoting(Long id) {
        return resultRepository.findAllFinalResultsForVoting(id);
    }

    @Override
    public List<Result> getAllResultsForVotingWith(Long id) {
        return resultRepository.findByVotingId(id);
    }

    @Override
    public Result findResultById(Long id){
        return resultRepository.findById(id)
                .orElseThrow(() -> new LoadFromDatabaseException());
    }

    @Override
    public List<Result> getAllEmptyResultsForUser() throws Exception {
        return resultRepository.findAllEmptyUserResults(mainService.getLoggedUser().getId());
    }

    @Override
    public void checkIfResultBelongToUser(Long resultId) throws Exception {
        resultRepository.checkIfResultBelongToUser(resultId, mainService.getLoggedUser().getId())
                .orElseThrow(() -> new AccessDeniedException());
    }

}
