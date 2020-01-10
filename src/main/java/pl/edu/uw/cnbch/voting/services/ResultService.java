package pl.edu.uw.cnbch.voting.services;

import pl.edu.uw.cnbch.voting.models.entities.Result;
import pl.edu.uw.cnbch.voting.models.entities.Voting;

import java.util.List;

public interface ResultService {

    void createEmptyResultsForAllUsersOf(Voting voting);

    List<Result> getAllResultsForVotingWith(Long id);

}
