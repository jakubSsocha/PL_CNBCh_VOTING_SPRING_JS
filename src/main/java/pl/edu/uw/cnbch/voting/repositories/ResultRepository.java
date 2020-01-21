package pl.edu.uw.cnbch.voting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.uw.cnbch.voting.models.entities.Result;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.entities.Voting;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    Optional<Result> findByVotingAndUser(Voting v, User u);

    @Query("select r from Result r where r.voting.id = :id and r.active = true")
    List<Result> findByVotingId(@Param("id") Long id);

    @Query("select r from Result r where r.id = :resultId and r.user.id = :userId")
    Optional<Result> checkIfResultBelongToUser(@Param("resultId") Long resultId,
                                               @Param("userId") Long userId);

    @Query("select r from Result r where r.voting.id = :votingId")
    List<Result> findAllByVotingId (@Param("votingId") Long id);

    @Query("select r from Result r where r.user.id = :userId and r.vote is null and r.active=true " +
            "and r.closed=false and r.voting.active = true and r.voting.closed = false")
    List<Result> findAllEmptyUserResults (@Param("userId") Long id);

    @Query("select r from Result r where r.closed = true and r.active = true and r.voting.id = :votingId")
    List<Result> findAllFinalResultsForVoting(@Param("votingId") Long votingId);
}
