package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("text","Witaj w systemie głosowania CNBCh UW");
        return "index.jsp"; }

    @GetMapping("/about")
    @ResponseBody
    public String about() { return "Here you can find some details for logged users"; }
}
