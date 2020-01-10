package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.uw.cnbch.voting.models.entities.Result;
import pl.edu.uw.cnbch.voting.services.ResultService;

import java.util.List;

@Controller
@RequestMapping("/result")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public List<Result> getAllResultForVotingWithId(@PathVariable Long id){
        return resultService.getAllResultsForVotingWith(id);
    }
}
