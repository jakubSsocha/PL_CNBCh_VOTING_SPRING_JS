package pl.edu.uw.cnbch.voting.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.edu.uw.cnbch.voting.models.entities.Voting;
import pl.edu.uw.cnbch.voting.repositories.VotingRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VotingServiceImpl implements VotingService {

    private final VotingRepository votingRepository;

    public VotingServiceImpl(VotingRepository votingRepository) {
        this.votingRepository = votingRepository;
    }

    @Override
    public Optional<Voting> findVotingByName(String votingName) {
        return votingRepository.findByName(votingName);
    }

    @Override
    public void createNew(Voting voting) {
        ifVotingDoesntExistSaveInDatabase(newVoting(voting));
    }

    private void ifVotingDoesntExistSaveInDatabase(Voting voting) {
        Optional<Voting> VotingFromDatabase = findVotingByName(voting.getName());
        if (VotingFromDatabase.isPresent()) {
            throw new DataIntegrityViolationException("Głosowanie o podanej nazwie już istnieje");
        } else {
            saveVotingInDatabase(voting);
        }
    }

    private void saveVotingInDatabase(Voting voting) {
        votingRepository.save(voting);
    }

    private Voting newVoting(Voting voting) {
        LocalDateTime now = LocalDateTime.now();
        voting.setCreatedDate(now);
        voting.setActive(true);
        voting.setClosed(false);
        return voting;
    }
}
