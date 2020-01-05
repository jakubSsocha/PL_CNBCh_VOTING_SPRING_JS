package pl.edu.uw.cnbch.voting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.uw.cnbch.voting.models.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
