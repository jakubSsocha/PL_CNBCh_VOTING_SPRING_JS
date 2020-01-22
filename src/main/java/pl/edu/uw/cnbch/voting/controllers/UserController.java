package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.viewDTO.MessageDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.UserExtendedDTO;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.ResultService;
import pl.edu.uw.cnbch.voting.services.UserService;
import pl.edu.uw.cnbch.voting.services.VotingService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final ResultService resultService;
    private final VotingService votingService;
    private final MainService mainService;

    public UserController(UserService userService,
                          ResultService resultService,
                          VotingService votingService,
                          MainService mainService) {
        this.userService = userService;
        this.resultService = resultService;
        this.votingService = votingService;
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
        try {
            model.addAttribute("allUsers", userService.findBasicInfoForAllUsers());
            return "allUsers.jsp";
        } catch (Exception e){
            model.addAttribute("message", MessageDTO.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
    }

    @RequestMapping("/user/{id}")
    @ResponseBody
    public UserExtendedDTO getExtendedUserData(@PathVariable Long id, Model model){
        try{
            return userService.findExtendedDataForUser(id);
        } catch (Exception e){
            return new UserExtendedDTO();
        }
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

    @RequestMapping("/user/results")
    public String goToVotingResults(Model model){
        try{
            model.addAttribute("votings", votingService.getAllUserClosedVoting());
        } catch (Exception e){
            model.addAttribute("message", MessageDTO.generateMessage(
                    e.getMessage(),
                    "error"
            ));
        }
        return "userResults.jsp";
    }

}
