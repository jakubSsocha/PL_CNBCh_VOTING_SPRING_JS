package pl.edu.uw.cnbch.voting.services;

import pl.edu.uw.cnbch.voting.models.entities.User;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);
}
