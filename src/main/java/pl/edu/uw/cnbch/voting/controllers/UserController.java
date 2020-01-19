package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.viewDTO.MessageHelper;
import pl.edu.uw.cnbch.voting.services.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/add")
    public String goToCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser.jsp";
    }

    @PostMapping("/user/add")
    public String validateAndCreateUser(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("message", MessageHelper.generateMessage(
                    "Nie udało się utworzyć użytkownika",
                    "error"));
            return "index.jsp";
        }

        try {

            userService.saveUser(user);

        } catch (DataIntegrityViolationException e) {

            model.addAttribute("message", MessageHelper.generateMessage(
                    "Użytkownik o podanym adresie e-mail:<br />" + user.getEmail() + "<br /> jest już zarejestrowany w bazie danych",
                    "error"));
            return "index.jsp";
        }
        model.addAttribute("message", MessageHelper.generateMessage(
                "Użytkownik utworzony pomyślnie",
                "success"));
        return "index.jsp";
    }

    @RequestMapping("/user/all")
    public String goToAllUsers(Model model){
        return "gogo";
    }


}
