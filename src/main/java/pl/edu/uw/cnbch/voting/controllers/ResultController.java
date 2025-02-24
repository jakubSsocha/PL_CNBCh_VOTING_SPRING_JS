package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.uw.cnbch.voting.models.entities.Result;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.ResultService;
import pl.edu.uw.cnbch.voting.services.SuccessMessageService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/result")
public class ResultController {

    private final String SUCCESS_VOTE_MESSAGE_TEXT = "Głos oddany pomyślnie";

    private final ResultService resultService;
    private final MainService mainService;
    private final SuccessMessageService successMessageService;

    @Autowired
    public ResultController(ResultService resultService,
                            MainService mainService,
                            SuccessMessageService successMessageService) {
        this.resultService = resultService;
        this.mainService = mainService;
        this.successMessageService = successMessageService;
    }

    @ModelAttribute("votingResults")
    public List<String> votingResults() {
        return Arrays.asList("ZA", "PRZECIW", "WSTRZYMUJĘ SIĘ");
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/{id}")
    @ResponseBody
    public List<Result> getAllResultForVotingWithId(@PathVariable Long id) {
        return resultService.getAllResultsForVotingWith(id);
    }

    @Secured("ROLE_USER")
    @GetMapping("/vote/{id}")
    public String goToVotingForm(@PathVariable Long id,
                                 Model model) throws Exception {
        resultService.checkIfResultBelongToUser(id);
        resultService.checkIfResultIsActive(id);
        model.addAttribute("result", resultService.findResultById(id));
        return "vote.jsp";
    }

    @Secured("ROLE_USER")
    @PostMapping("/vote/{id}")
    public String createVotingResult(@Valid Result result,
                                     BindingResult bindingResult,
                                     Model model) throws Exception {
        mainService.checkForErrorsIn(bindingResult);
        resultService.saveUserVoteFor(result);
        successMessageService.addMessageTo(model,
                SUCCESS_VOTE_MESSAGE_TEXT);
        return "index.jsp";
    }

}
