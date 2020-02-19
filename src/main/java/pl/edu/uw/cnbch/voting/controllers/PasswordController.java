package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.uw.cnbch.voting.models.viewDTO.SetNewPasswordDTO;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.PasswordService;
import pl.edu.uw.cnbch.voting.services.SuccessMessageService;

import javax.validation.Valid;

@Controller
@RequestMapping("/password")
public class PasswordController {

    private final String SUCCESS_PASSWORD_CHANGE_MESSAGE = "Hasło zmienione pomyślnie";

    private final PasswordService passwordService;
    private final MainService mainService;
    private final SuccessMessageService successMessageService;

    @Autowired
    public PasswordController(PasswordService passwordService,
                              MainService mainService,
                              SuccessMessageService successMessageService) {
        this.passwordService = passwordService;
        this.mainService = mainService;
        this.successMessageService = successMessageService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/setNew")
    public String goToSetNewPasswordForm(Model model) {
        model.addAttribute("passwordChanger", new SetNewPasswordDTO());
        return "setNewPassword.jsp";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/setNew")
    public String setNewPasswordForUser(@Valid SetNewPasswordDTO setNewPasswordDTO,
                                        BindingResult bindingResult,
                                        Model model) throws Exception {
        mainService.checkForErrorsIn(bindingResult);
        passwordService.setNewPassword(setNewPasswordDTO);
        successMessageService.addMessageTo(model,
                SUCCESS_PASSWORD_CHANGE_MESSAGE);
        return "index.jsp";
    }

    //TODO - password reset mechanism
    @RequestMapping("/reset")
    private String resetPassword() {
        passwordService.resetPassword();
        return "index.jsp";
    }
}
