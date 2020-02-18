package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.stereotype.Service;
import pl.edu.uw.cnbch.voting.models.entities.Role;
import pl.edu.uw.cnbch.voting.repositories.RoleRepository;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.RoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final MainService mainService;

    public RoleServiceImpl(RoleRepository roleRepository, MainService mainService) {
        this.roleRepository = roleRepository;
        this.mainService = mainService;
    }

    @Override
    public Role findByName(String name) throws Exception {
        Optional<Role> role = roleRepository.findByName(name);
        mainService.checkIfOptionalIsEmpty(role);
        return role.get();
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
