package pl.edu.uw.cnbch.voting.services;

import pl.edu.uw.cnbch.voting.models.entities.Voting;

public interface ResultService {

    void createEmptyResultsForAllUsersOf(Voting voting);

}
