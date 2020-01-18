package pl.edu.uw.cnbch.voting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.uw.cnbch.voting.models.entities.Voting;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Long> {

    Optional<Voting> findByName(String name);

    @Query("select v from Voting v order by v.closed desc, v.createdDate desc")
    List<Voting> selectAllVotingBasicInfoOrdered();

    @Query("select v.id, v.description, v.createdDate, v.closed, v.closedDate, v.active from Voting v where v.id = :id")
    List<Voting> findVotingByID(@Param("id") Long id);
}
