package pl.edu.uw.cnbch.voting.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.uw.cnbch.voting.models.viewDTO.RolesDTO;
import pl.edu.uw.cnbch.voting.services.*;

import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ResultService resultService;

    @MockBean
    private VotingService votingService;

    @MockBean
    private MainService mainService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private SuccessMessageService successMessageService;

    @MockBean
    private ErrorMessageService errorMessageService;

    @Test
    public void go_to_create_user_form() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("createUser.jsp"));
    }

    @Test
    public void validate_and_create_user() throws Exception {
        mockMvc.perform(post("/user/add").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("index.jsp"));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void go_to_all_users_user() throws Exception {
        mockMvc.perform(get("/user/all"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void go_to_all_users_admin() throws Exception {
        mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("allUsers.jsp"));
    }

    @Test
    public void go_to_all_users_noLogInUser() throws Exception {
        mockMvc.perform(get("/user/all"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void get_extended_user_data_user() throws Exception {
        mockMvc.perform(get("/user/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void get_extended_user_data_admin() throws Exception {
        mockMvc.perform(get("/user/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void get_extended_user_data_noLogInUser() throws Exception {
        mockMvc.perform(get("/user/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void go_to_all_user_active_voting_user() throws Exception {
        mockMvc.perform(get("/user/votings"))
                .andExpect(status().isOk())
                .andExpect(view().name("userVotings.jsp"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void go_to_all_user_active_voting_admin() throws Exception {
        mockMvc.perform(get("/user/votings"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void go_to_all_user_active_voting_noLogInUser() throws Exception {
        mockMvc.perform(get("/user/votings"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void go_to_voting_results_user() throws Exception {
        mockMvc.perform(get("/user/results"))
                .andExpect(status().isOk())
                .andExpect(view().name("userResults.jsp"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void go_to_voting_results_admin() throws Exception {
        mockMvc.perform(get("/user/results"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void go_to_voting_results_noLogInUser() throws Exception {
        mockMvc.perform(get("/user/results"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void deactivate_user_user() throws Exception {
        mockMvc.perform(get("/user/deactivate/{id}", 1))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void deactivate_user_admin() throws Exception {
        mockMvc.perform(get("/user/deactivate/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("index.jsp"));
    }

    @Test
    public void deactivate_user_noLogInUser() throws Exception {
        mockMvc.perform(get("/user/deactivate/{id}", 1))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void activate_user_user() throws Exception {
        mockMvc.perform(get("/user/activate/{id}", 1))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void activate_user_admin() throws Exception {
        mockMvc.perform(get("/user/activate/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("index.jsp"));
    }

    @Test
    public void activate_user_noLogInUser() throws Exception {
        mockMvc.perform(get("/user/activate/{id}", 1))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void go_to_change_role_form_user() throws Exception {
        mockMvc.perform(get("/user/changeRole/{id}", 1))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void go_to_change_role_form_admin() throws Exception {
        mockMvc.perform(get("/user/changeRole/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("changeRoles.jsp"));
    }

    @Test
    public void go_to_change_role_form_noLogInUser() throws Exception {
        mockMvc.perform(get("/user/changeRole/{id}", 1))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void change_user_roles_user() throws Exception {
        mockMvc.perform(post("/user/changeRole/{id}", 1)
                .with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void change_user_roles_admin() throws Exception {
        mockMvc.perform(post("/user/changeRole/{id}", 1)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("index.jsp"));
    }

    @Test
    public void change_user_roles_noLogInUser() throws Exception {
        mockMvc.perform(post("/user/changeRole/{id}", 1)
                .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

}