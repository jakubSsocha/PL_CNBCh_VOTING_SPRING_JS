package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.services.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/add")
    public String goToCreateUserForm(Model model){
        model.addAttribute("user", new User());
        return "createUser.jsp";
    }

    @PostMapping("/user/add")
    public String validateAndCreateUser(@Valid User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("text", "Nie udało się utworzyć użytkownika");
            return "index.jsp";
        }
        userService.saveUser(user);
        model.addAttribute("text", "Użytkownik utworzony pomyślnie");
        return "index.jsp";
    }

}
