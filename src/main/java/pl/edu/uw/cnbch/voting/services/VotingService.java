package pl.edu.uw.cnbch.voting.services;

import pl.edu.uw.cnbch.voting.models.entities.Voting;

import java.util.Optional;

public interface VotingService {

    void createNew(Voting voting);

    Optional<Voting> findVotingByName(String string);

}
