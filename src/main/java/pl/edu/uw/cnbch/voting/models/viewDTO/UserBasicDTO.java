package pl.edu.uw.cnbch.voting.models.viewDTO;

import pl.edu.uw.cnbch.voting.models.entities.User;

public class UserBasicDTO {

    private Long id;
    private String name;
    private boolean active;

    public UserBasicDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        setActiveValue(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private void setActiveValue(User user){
        if(user.getEnabled() == 1){
            this.active = true;
        } else {
            this.active = false;
        }
    }
}
