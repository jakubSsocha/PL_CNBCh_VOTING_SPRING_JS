package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.uw.cnbch.voting.models.entities.Result;
import pl.edu.uw.cnbch.voting.models.viewDTO.MessageHelper;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.ResultService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/result")
public class ResultController {

    private final ResultService resultService;
    private final MainService mainService;

    public ResultController(ResultService resultService, MainService mainService) {
        this.resultService = resultService;
        this.mainService = mainService;
    }

    @ModelAttribute("votingResults")
    public List<String> votingResults(){
        return Arrays.asList("TAK", "NIE", "WSTRZYMUJĘ SIĘ");
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public List<Result> getAllResultForVotingWithId(@PathVariable Long id) {
        return resultService.getAllResultsForVotingWith(id);
    }

    @GetMapping("/vote/{id}")
    public String goToVotingForm(Model model, @PathVariable Long id) {
        try {
            resultService.checkIfResultBelongToUser(id);
            resultService.checkIfResultIsActive(id);
            model.addAttribute("result", resultService.findById(id));
        } catch (Exception e) {
            model.addAttribute("message", MessageHelper.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
        return "vote.jsp";
    }

    @PostMapping("/vote/{id}")
    public String createVotingResult(@Valid Result result, BindingResult bindingResult, Model model){
        try{
            mainService.checkForErrorsIn(bindingResult);
            resultService.saveUserVoteFor(result);
        } catch (Exception e){
            model.addAttribute("message", MessageHelper.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
        model.addAttribute("message", MessageHelper.generateMessage(
                "Głos oddany pomyślnie",
                "success"
        ));
        return "index.jsp";
    }

}
