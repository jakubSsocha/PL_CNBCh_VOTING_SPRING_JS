package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.uw.cnbch.voting.models.viewHelpers.MessageHelper;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message",MessageHelper.generateMessage(
                "Witaj w systemie głosowania CNBCh UW",
                "success"));
        return "index.jsp"; }

    @GetMapping("/about")
    @ResponseBody
    public String about() { return "Here you can find some details for logged users"; }
}
