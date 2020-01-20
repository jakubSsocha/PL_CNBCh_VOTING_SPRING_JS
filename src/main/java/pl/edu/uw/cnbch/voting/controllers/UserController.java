package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.viewDTO.MessageDTO;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.ResultService;
import pl.edu.uw.cnbch.voting.services.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final ResultService resultService;
    private final MainService mainService;

    public UserController(UserService userService,
                          ResultService resultService,
                          MainService mainService) {
        this.userService = userService;
        this.resultService = resultService;
        this.mainService = mainService;
    }

    @GetMapping("/user/add")
    public String goToCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser.jsp";
    }

    @PostMapping("/user/add")
    public String validateAndCreateUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", MessageDTO.generateMessage(
                    "Nie udało się utworzyć użytkownika",
                    "error"));
            return "index.jsp";
        }
        try {
            userService.saveUser(user);
        } catch (DataIntegrityViolationException e) {

            model.addAttribute("message", MessageDTO.generateMessage(
                    "Użytkownik o podanym adresie e-mail:<br />" + user.getEmail() + "<br /> jest już zarejestrowany w bazie danych",
                    "error"));
            return "index.jsp";
        }
        model.addAttribute("message", MessageDTO.generateMessage(
                "Użytkownik utworzony pomyślnie",
                "success"));
        return "index.jsp";
    }

    @RequestMapping("/user/all")
    public String goToAllUsers(Model model) {
        return "gogo";
    }

    @RequestMapping("/user/votings")
    public String goToAllUserActiveVoting(Model model) {
        try {
            model.addAttribute("results", resultService.getAllEmptyResultsForUser());
        } catch (Exception e) {
            model.addAttribute("message", MessageDTO.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
        return "userVotings.jsp";
    }

}
