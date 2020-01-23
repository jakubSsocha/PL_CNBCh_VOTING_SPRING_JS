package pl.edu.uw.cnbch.voting.services;

import pl.edu.uw.cnbch.voting.models.entities.Role;

import java.util.List;

public interface RoleService {

    Role findByName(String name) throws Exception;

    List<Role> findAllRoles();
}
