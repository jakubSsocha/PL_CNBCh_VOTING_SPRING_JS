package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.uw.cnbch.voting.models.viewDTO.SetNewPasswordDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.MessageDTO;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.PasswordService;

import javax.validation.Valid;

@Controller
@RequestMapping("/password")
public class PasswordController {

    private final PasswordService passwordService;
    private final MainService mainService;

    public PasswordController(PasswordService passwordService,
                              MainService mainService) {
        this.passwordService = passwordService;
        this.mainService = mainService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/setNew")
    public String goToSetNewPasswordForm(Model model){
        model.addAttribute("passwordChanger", new SetNewPasswordDTO());
        return "setNewPassword.jsp";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/setNew")
    public String setUserNew(@Valid SetNewPasswordDTO setNewPasswordDTO,
                             BindingResult bindingResult,
                             Model model){
        try{
            mainService.checkForErrorsIn(bindingResult);
            passwordService.setNewPassword(setNewPasswordDTO);
        } catch (Exception e){
            model.addAttribute("message", MessageDTO.generateMessage(
                    e.getMessage(),
                    "error"
            ));
            return "index.jsp";
        }
        model.addAttribute("message", MessageDTO.generateMessage(
                "Hasło zmienione pomyślnie",
                "success"
        ));
        return "index.jsp";
    }

    //TODO - password reset mechanism
    @RequestMapping("/reset")
    private String resetPassword(){
        passwordService.resetPassword();
        return "index.jsp";
    }
}
