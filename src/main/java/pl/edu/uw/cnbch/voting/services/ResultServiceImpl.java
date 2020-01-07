package pl.edu.uw.cnbch.voting.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.edu.uw.cnbch.voting.models.entities.Result;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.entities.Voting;
import pl.edu.uw.cnbch.voting.repositories.ResultRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public void createEmptyResultsForAllUsersOf(Voting voting) {
        LocalDateTime currentTime = LocalDateTime.now();
        for (User user : voting.getUsers()) {
            ifResultDoesntExistsSaveInDatabase(newEmptyResultFor(voting, user, currentTime));
        }
    }

    private Result newEmptyResultFor(Voting voting, User user, LocalDateTime time) {
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
        if (ResultFromDatabase.isPresent()) {
            throw new DataIntegrityViolationException("Rezultat dla podanego głosowania i użytkownika już istniej");
        } else {
            resultRepository.save(result);
        }
    }

}
