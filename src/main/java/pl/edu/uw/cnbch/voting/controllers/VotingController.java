package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.entities.Voting;
import pl.edu.uw.cnbch.voting.models.viewHelpers.MessageHelper;
import pl.edu.uw.cnbch.voting.services.ResultService;
import pl.edu.uw.cnbch.voting.services.UserService;
import pl.edu.uw.cnbch.voting.services.VotingService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/voting")
public class VotingController {

    private final VotingService votingService;
    private final ResultService resultService;
    private final UserService userService;

    public VotingController(VotingService votingService, ResultService resultService, UserService userService) {
        this.votingService = votingService;
        this.resultService = resultService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String goToAddNewVotingForm(Model model){
        model.addAttribute("voting", new Voting());
        return "createVoting.jsp";
    }

    @PostMapping("/add")
    public String addNewVotingAndEmptyUsersResults(@Valid Voting voting, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", MessageHelper.generateMessage(
                    "Przynajmniej jedno pole zawiera nieprawidłowe dane - spróbuj ponownie",
                    "error"));
            return "createVoting.jsp";
        }
        try {
            votingService.createNew(voting);
            Optional<Voting> VotingFromDatabase = votingService.findVotingByName(voting.getName());
            if(VotingFromDatabase.isPresent()){
                resultService.createEmptyResultsForAllUsersOf(VotingFromDatabase.get());
            } else {
                model.addAttribute("message", MessageHelper.generateMessage(
                        "Nie udało się znaleźć tego głosowania w bazie danych - skontaktuj się z administratorem",
                        "error"));
                return "index.jsp";
            }
        } catch (DataIntegrityViolationException dive) {
            model.addAttribute("message", MessageHelper.generateMessage(
                    dive.getMessage(),
                    "error"));
            return "index.jsp";
        }
        model.addAttribute("message", MessageHelper.generateMessage(
                "Utworzono głosowanie i puste głosy dla wskazanych użytkowników",
                "success"));
        return "index.jsp";
    }

    @ModelAttribute("allUsers")
    public List<User> getAllUsersList(){
        return userService.findAllActiveUsers();
    }

}
