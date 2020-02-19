package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.stereotype.Service;
import pl.edu.uw.cnbch.voting.errors.types.LoadFromDatabaseException;
import pl.edu.uw.cnbch.voting.models.entities.Role;
import pl.edu.uw.cnbch.voting.repositories.RoleRepository;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.RoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(
                () -> new LoadFromDatabaseException()
        );
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
