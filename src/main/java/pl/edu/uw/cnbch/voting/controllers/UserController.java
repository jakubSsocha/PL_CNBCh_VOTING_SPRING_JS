package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.stereotype.Controller;
import pl.edu.uw.cnbch.voting.services.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

}
