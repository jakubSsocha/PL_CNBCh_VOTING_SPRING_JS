package pl.edu.uw.cnbch.voting.models.viewDTO;

import pl.edu.uw.cnbch.voting.models.entities.Role;
import pl.edu.uw.cnbch.voting.models.entities.User;

import java.util.Set;

public class RolesDTO {

    private Set<Role> roles;

    private String name;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RolesDTO(User user) {
        this.roles = user.getRoles();
        this.name = user.getName();
    }

}
