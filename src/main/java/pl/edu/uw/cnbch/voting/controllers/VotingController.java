package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.entities.Voting;
import pl.edu.uw.cnbch.voting.models.viewDTO.MessageHelper;
import pl.edu.uw.cnbch.voting.models.viewDTO.AllVotingDTO;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.ResultService;
import pl.edu.uw.cnbch.voting.services.UserService;
import pl.edu.uw.cnbch.voting.services.VotingService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/voting")
public class VotingController {

    private final VotingService votingService;
    private final ResultService resultService;
    private final UserService userService;
    private final MainService mainService;

    public VotingController(VotingService votingService, ResultService resultService, UserService userService, MainService mainService) {
        this.votingService = votingService;
        this.resultService = resultService;
        this.userService = userService;
        this.mainService = mainService;
    }

    @ModelAttribute("allUsers")
    public List<User> getAllUsersList() {
        return userService.findAllActiveUsers();
    }

    @GetMapping("/add")
    public String goToAddNewVotingForm(Model model) {
        model.addAttribute("voting", new Voting());
        return "createVoting.jsp";
    }

    @PostMapping("/add")
    public String addNewVotingAndEmptyUsersResults(@Valid Voting voting, BindingResult bindingResult, Model model) {
        try {
            mainService.checkForErrorsIn(bindingResult);
            votingService.create(voting);
            Voting VotingFromDatabase = votingService.readByName(voting.getName());
            resultService.createActiveResultsForAllUsersOf(VotingFromDatabase);
        } catch (Exception e) {
            model.addAttribute("message", MessageHelper.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
        model.addAttribute("message", MessageHelper.generateMessage(
                "Utworzono głosowanie i puste głosy dla wskazanych użytkowników",
                "success"));
        return "index.jsp";
    }

    @GetMapping("/all")
    public String goToAllVotingView(Model model) {
        model.addAttribute("allVotings", getVotingIDdate());
        return "allVotings.jsp";
    }

    private List<AllVotingDTO> getVotingIDdate() {
        List<AllVotingDTO> allVotingDTO = new ArrayList<>();
        for (Voting v : votingService.getAllActiveVotingData()) {
            allVotingDTO.add(new AllVotingDTO(v));
        }
        return allVotingDTO;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Voting returnAllDataForVotingId(@PathVariable Long id) {
        try {
            return votingService.readById(id);
        } catch (Exception e) {
        }
        return new Voting();
    }

    @GetMapping("/edit/{id}")
    public String goToEditForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("voting", votingService.readById(id));
            return "createVoting.jsp";
        } catch (Exception e) {
            model.addAttribute("message", MessageHelper.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
    }

    @PostMapping("/edit/{id}")
    public String editVotingData(@Valid Voting voting,
                                 BindingResult bindingResult,
                                 Model model) {
        try {
            mainService.checkForErrorsIn(bindingResult);
            votingService.edit(voting);
        } catch (Exception e) {
            model.addAttribute("message", MessageHelper.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
        model.addAttribute("message", MessageHelper.generateMessage(
                "Głosowanie poprawnie zmodyfikowane",
                "success"));
        return "index.jsp";
    }

    @GetMapping("/delete/{id}")
    public String goToDeleteForm(@PathVariable Long id, Model model) {
        try {
            votingService.checkIfClosed(id);
            model.addAttribute("voting", votingService.readById(id));
            model.addAttribute("message", MessageHelper.generateMessage(
                    "Usuwasz głosowanie - ta operacja jest nieodwracalna!",
                    "error"
            ));
            return "deleteVoting.jsp";
        } catch (Exception e) {
            model.addAttribute("message", MessageHelper.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteVoting(@ModelAttribute Voting voting, Model model) {
        try {
            votingService.delete(voting);
        } catch (Exception e) {
            model.addAttribute("message", MessageHelper.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
        return "index.jsp";
    }

    @GetMapping("/close/{id}")
    public String goToCloseForm(@PathVariable Long id, Model model){
        try {
            votingService.checkIfClosed(id);
            model.addAttribute("voting", votingService.readById(id));
            model.addAttribute("message", MessageHelper.generateMessage(
                    "Zamykasz głosowanie - ta operacja jest nieodwracalna!",
                    "error"
            ));
            return "closeVoting.jsp";
        } catch (Exception e){
            model.addAttribute("message", MessageHelper.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
    }

    @PostMapping("/close/{id}")
    public String closeVoting(@ModelAttribute Voting voting, Model model){
        try{
            votingService.checkIfClosed(voting.getId());
            votingService.close(voting);
            model.addAttribute("message", MessageHelper.generateMessage(
                    "Głosowanie zostało zakończone!",
                    "success"
            ));
            return "index.jsp";
        } catch (Exception e){
            model.addAttribute("message", MessageHelper.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
    }

    @RequestMapping("/result/{id}")
    public String goToResultForm(@PathVariable Long id, Model model){
        try{
            model.addAttribute("voting", votingService.readById(id));
            return "votingResult.jsp";
        } catch (Exception e){
            model.addAttribute("message", MessageHelper.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
    }
}
