package pl.edu.uw.cnbch.voting.models.viewDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.edu.uw.cnbch.voting.models.entities.Result;
import pl.edu.uw.cnbch.voting.models.entities.Role;
import pl.edu.uw.cnbch.voting.models.entities.User;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserExtendedDTO {

    @JsonIgnore
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");

    private String name;
    private boolean active;
    private String email;
    private String createdDate;
    private List<String> roleList;
    private List<Result> resultList;

    public UserExtendedDTO(User user) {
        this.name = user.getName();
        setActiveValue(user);
        this.email = user.getEmail();
        this.createdDate = user.getCreatedDate().format(formatter);
        setUserRoleList(user);
        this.resultList = user.getResults();
    }

    public UserExtendedDTO() {
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    private void setActiveValue(User user){
        if(user.getEnabled() == 1){
            this.active = true;
        } else {
            this.active = false;
        }
    }

    private void setUserRoleList(User user){
        List<String> userRoles = new ArrayList<>();
        for(Role r: user.getRoles()){
            if(r.getName().equals("ROLE_USER")){
                userRoles.add("Użytkownik");
            } else if(r.getName().equals("ROLE_ADMIN")){
                userRoles.add("Administrator");
            } else {
                userRoles.add(r.getName());
            }
        }
        this.roleList = userRoles;
    }
}
