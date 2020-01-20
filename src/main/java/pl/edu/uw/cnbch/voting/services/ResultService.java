package pl.edu.uw.cnbch.voting.services;

import pl.edu.uw.cnbch.voting.models.entities.Result;
import pl.edu.uw.cnbch.voting.models.entities.Voting;

import java.time.LocalDateTime;
import java.util.List;

public interface ResultService {

    void createActiveResultsForAllUsersOf(Voting voting);

    List<Result> getAllResultsForVotingWith(Long id);

    Result findById(Long id) throws Exception;

    void save(Result result);

    void saveUserVoteFor(Result result) throws Exception;

    void checkIfResultIsActive(Long id) throws Exception;

    void checkIfResultBelongToUser(Long resultId) throws Exception;

    void setAllResultsInactiveForVotingId(Long id) throws Exception;

    void selAllResultsClosedForVotingId(Long id, LocalDateTime localDateTime) throws Exception;

    void encodeAllResultsForVotingId(Long id) throws Exception;

    List<Result> getAllEmptyResultsForUser() throws Exception;
}
