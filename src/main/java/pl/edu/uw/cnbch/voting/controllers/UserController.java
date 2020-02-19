package pl.edu.uw.cnbch.voting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.edu.uw.cnbch.voting.models.entities.Role;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.viewDTO.MessageDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.RolesDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.UserExtendedDTO;
import pl.edu.uw.cnbch.voting.services.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final String SUCCESS_USER_CREATED =
            "Użytkownik utworzony pomyślnie. Konto wymaga akceptacji administratora";
    private final String SUCCESS_USER_DEACTIVATED = "Użytkownik został dezaktywowany";
    private final String SUCCESS_USER_ACTIVATED = "Użytkownik został aktywowany";
    private final String SUCCESS_USER_ROLES_CHANGED = "Zmiana ról użytkownika zakończona sukcesem";

    private final UserService userService;
    private final ResultService resultService;
    private final VotingService votingService;
    private final MainService mainService;
    private final RoleService roleService;
    private final SuccessMessageService successMessageService;

    @Autowired
    public UserController(UserService userService,
                          ResultService resultService,
                          VotingService votingService,
                          MainService mainService,
                          RoleService roleService,
                          SuccessMessageService successMessageService) {
        this.userService = userService;
        this.resultService = resultService;
        this.votingService = votingService;
        this.mainService = mainService;
        this.roleService = roleService;
        this.successMessageService = successMessageService;
    }

    @ModelAttribute("Roles")
    public List<Role> getRoleList() {
        return roleService.findAllRoles();
    }

    @GetMapping("/add")
    public String goToCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser.jsp";
    }

    @PostMapping("/add")
    public String validateAndCreateUser(@Valid User user,
                                        BindingResult bindingResult,
                                        Model model)
            throws Exception {
        mainService.checkForErrorsIn(bindingResult);
        userService.saveInactiveUser(user);
        successMessageService.addMessageTo(model,
                SUCCESS_USER_CREATED);
        return "index.jsp";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/all")
    public String goToAllUsers(Model model) {
        model.addAttribute("allUsers", userService.findBasicInfoForAllUsers());
        return "allUsers.jsp";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/{id}")
    @ResponseBody
    public UserExtendedDTO getExtendedUserData(@PathVariable Long id)
            throws Exception {
        return userService.findExtendedDataForUser(id);
    }

    @Secured("ROLE_USER")
    @RequestMapping("/votings")
    public String goToAllUserActiveVoting(Model model)
            throws Exception {
        model.addAttribute("results", resultService.getAllEmptyResultsForUser());
        return "userVotings.jsp";
    }

    @Secured("ROLE_USER")
    @RequestMapping("/results")
    public String goToVotingResults(Model model)
            throws Exception {
        model.addAttribute("votings", votingService.getAllUserClosedVoting());
        return "userResults.jsp";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/deactivate/{id}")
    public String deactivateUser(@PathVariable Long id,
                                 Model model)
            throws Exception {
        userService.deactivateUserWithId(id);
        successMessageService.addMessageTo(model,
                SUCCESS_USER_DEACTIVATED);
        return "index.jsp";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/activate/{id}")
    public String activateUser(@PathVariable Long id,
                               Model model)
            throws Exception {
        userService.activateUserWithId(id);
        successMessageService.addMessageTo(model,
                SUCCESS_USER_ACTIVATED);
        return "index.jsp";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/changeRole/{id}")
    public String goToChangeRoleForm(@PathVariable Long id,
                                     Model model)
            throws Exception {
        User user = userService.findByUserId(id);
        model.addAttribute("RolesDTO", new RolesDTO(user));
        return "changeRoles.jsp";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/changeRole/{id}")
    public String goToChangeRoleForm(@ModelAttribute RolesDTO rolesDTO,
                                     @PathVariable Long id,
                                     Model model)
            throws Exception {
        userService.changeRoles(id, rolesDTO);
        successMessageService.addMessageTo(model,
                SUCCESS_USER_ROLES_CHANGED);
        return "index.jsp";
    }
}
