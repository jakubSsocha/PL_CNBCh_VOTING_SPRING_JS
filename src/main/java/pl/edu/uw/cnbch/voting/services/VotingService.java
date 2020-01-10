package pl.edu.uw.cnbch.voting.services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.edu.uw.cnbch.voting.models.entities.Voting;

import java.util.List;
import java.util.Optional;

public interface VotingService {

    void createNew(Voting voting);

    Optional<Voting> findVotingByName(String string);

    List<Voting> getAllVotingsIdTextNameAndClosed();

    @Query("select v.id, v.description, v.createdDate, v.closed, v.closedDate, v.active from Voting v where v.id = :id")
    Optional<Voting> findVotingByID(@Param("id") Long id);

}
