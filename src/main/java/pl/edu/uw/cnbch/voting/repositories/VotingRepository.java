package pl.edu.uw.cnbch.voting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.uw.cnbch.voting.models.entities.Voting;

import java.util.Optional;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Long> {

    Optional<Voting> findByName(String name);
}
