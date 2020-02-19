package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.entities.Voting;
import pl.edu.uw.cnbch.voting.models.viewDTO.VotingDetailsDTO;
import pl.edu.uw.cnbch.voting.services.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/voting")
public class VotingController {

    private final String SUCCESS_NEW_VOTING_AND_RESULTS_CREATED =
            "Utworzono głosowanie i puste głosy dla wskazanych użytkowników";
    private final String SUCCESS_VOTING_MODIFIED = "Głosowanie poprawnie zmodyfikowane";
    private final String SUCCESS_VOTING_CLOSED = "Głosowanie zostało zakończone!";
    private final String ERROR_VOTING_DELETE_MESSAGE = "Usuwasz głosowanie - ta operacja jest nieodwracalna!";
    private final String ERROR_VOTING_CLOSE_MESSAGE = "Zamykasz głosowanie - ta operacja jest nieodwracalna!";

    private final VotingService votingService;
    private final ResultService resultService;
    private final UserService userService;
    private final MainService mainService;
    private final SuccessMessageService successMessageService;
    private final ErrorMessageService errorMessageService;

    @Autowired
    public VotingController(VotingService votingService,
                            ResultService resultService,
                            UserService userService,
                            MainService mainService,
                            SuccessMessageService successMessageService,
                            ErrorMessageService errorMessageService) {
        this.votingService = votingService;
        this.resultService = resultService;
        this.userService = userService;
        this.mainService = mainService;
        this.successMessageService = successMessageService;
        this.errorMessageService = errorMessageService;
    }

    @ModelAttribute("allUsers")
    public List<User> getAllUsersList() {
        return userService.findAllActiveUsers();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/add")
    public String goToAddNewVotingForm(Model model) {
        model.addAttribute("voting", new Voting());
        return "createVoting.jsp";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public String addNewVotingAndEmptyUsersResults(@Valid Voting voting,
                                                   BindingResult bindingResult,
                                                   Model model)
            throws Exception {
        mainService.checkForErrorsIn(bindingResult);
        votingService.create(voting);
        Voting VotingFromDatabase = votingService.readByName(voting.getName());
        resultService.createActiveResultsForAllUsersFor(VotingFromDatabase);
        successMessageService.addMessageTo(model,
                SUCCESS_NEW_VOTING_AND_RESULTS_CREATED);
        return "index.jsp";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public String goToAllVotingView(Model model) {
        model.addAttribute("allVotings", votingService.getAllVotingIdData());
        return "allVotings.jsp";
    }

    @ResponseBody
    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    public VotingDetailsDTO returnAllDataForVotingId(@PathVariable Long id)
            throws Exception {
        votingService.checkIfActive(id);
        return votingService.getDetailsForVoting(id);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/edit/{id}")
    public String goToEditForm(@PathVariable Long id,
                               Model model)
            throws Exception {
        votingService.checkIfClosed(id);
        votingService.checkIfActive(id);
        model.addAttribute("voting", votingService.getVotingBy(id));
        return "editVoting.jsp";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/edit/{id}")
    public String editVotingData(@Valid Voting voting,
                                 BindingResult bindingResult,
                                 Model model)
            throws Exception {
        mainService.checkForErrorsIn(bindingResult);
        votingService.edit(voting);
        successMessageService.addMessageTo(model,
                SUCCESS_VOTING_MODIFIED);
        return "index.jsp";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public String goToDeleteForm(@PathVariable Long id,
                                 Model model)
            throws Exception {
        votingService.checkIfClosed(id);
        votingService.checkIfActive(id);
        model.addAttribute("voting", votingService.getVotingBy(id));
        errorMessageService.addMessageTo(model,
                ERROR_VOTING_DELETE_MESSAGE);
        return "deleteVoting.jsp";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/delete/{id}")
    public String deleteVoting(@ModelAttribute Voting voting)
            throws Exception {
        votingService.delete(voting);
        return "index.jsp";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/close/{id}")
    public String goToCloseForm(@PathVariable Long id,
                                Model model)
            throws Exception {
        votingService.checkIfClosed(id);
        votingService.checkIfActive(id);
        model.addAttribute("voting", votingService.getVotingBy(id));
        errorMessageService.addMessageTo(model,
                ERROR_VOTING_CLOSE_MESSAGE);
        return "closeVoting.jsp";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/close/{id}")
    public String closeVoting(@ModelAttribute Voting voting,
                              Model model)
            throws Exception {
        votingService.checkIfClosed(voting.getId());
        votingService.close(voting);
        successMessageService.addMessageTo(model,
                SUCCESS_VOTING_CLOSED);
        return "index.jsp";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping("/result/{id}")
    public String goToResultForm(@PathVariable Long id,
                                 Model model)
            throws Exception {
        votingService.checkIfActive(id);
        model.addAttribute("votingResult", votingService.generateResultForVoting(id));
        model.addAttribute("voting", votingService.getVotingBy(id));
        return "votingResult.jsp";
    }
}
