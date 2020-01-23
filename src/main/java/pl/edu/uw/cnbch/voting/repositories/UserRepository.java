package pl.edu.uw.cnbch.voting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.uw.cnbch.voting.models.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("select u from User u order by u.id desc")
    List<User> findAllUsers();

    @Query("select u from User u where u.id = :id")
    Optional<User> findUserByID(@Param("id") Long id);

    @Query("select u from User u where u.enabled = 1 order by u.lastName asc")
    List<User> findAllActiveUsers();
}
