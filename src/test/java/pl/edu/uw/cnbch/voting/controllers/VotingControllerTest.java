package pl.edu.uw.cnbch.voting.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.uw.cnbch.voting.errors.ErrorManager;
import pl.edu.uw.cnbch.voting.services.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(VotingController.class)
public class VotingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotingService votingService;

    @MockBean
    private ResultService resultService;

    @MockBean
    private UserService userService;

    @MockBean
    private MainService mainService;

    @MockBean
    private SuccessMessageService successMessageService;

    @MockBean
    private ErrorMessageService errorMessageService;

    @MockBean
    private ErrorManager errorManager;

    @WithMockUser(roles = "USER")
    @Test
    public void go_to_add_new_voting_form_user() throws Exception {
        mockMvc.perform(get("/voting/add"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void go_to_add_new_voting_form_admin() throws Exception {
        mockMvc.perform(get("/voting/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("createVoting.jsp"));
    }

    @Test
    public void go_to_add_new_voting_form_noLogInUser() throws Exception {
        mockMvc.perform(get("/voting/add"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void add_new_voting_and_empty_user_results_user() throws Exception {
        mockMvc.perform(post("/voting/add").with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void add_new_voting_and_empty_user_results_admin() throws Exception {
        mockMvc.perform(post("/voting/add").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("index.jsp"));
    }

    @Test
    public void add_new_voting_and_empty_user_results_noLogInUser() throws Exception {
        mockMvc.perform(post("/voting/add").with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void go_to_all_voting_view_user() throws Exception {
        mockMvc.perform(get("/voting/all"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void go_to_all_voting_view_admin() throws Exception {
        mockMvc.perform(get("/voting/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("allVotings.jsp"));
    }

    @Test
    public void go_to_all_voting_view_noLogInUser() throws Exception {
        mockMvc.perform(get("/voting/all"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void return_all_data_for_voting_id_user() throws Exception {
        mockMvc.perform(get("/voting/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void return_all_data_for_voting_id_admin() throws Exception {
        mockMvc.perform(get("/voting/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void return_all_data_for_voting_id_noLogInUser() throws Exception {
        mockMvc.perform(get("/voting/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void go_to_edit_form_user() throws Exception {
        mockMvc.perform(get("/voting/edit/{id}", 1))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void go_to_edit_form_admin() throws Exception {
        mockMvc.perform(get("/voting/edit/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("editVoting.jsp"));
    }

    @Test
    public void go_to_edit_form_noLogInUser() throws Exception {
        mockMvc.perform(get("/voting/edit/{id}", 1))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void edit_voting_data_user() throws Exception {
        mockMvc.perform(post("/voting/edit/{id}", 1).with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void edit_voting_data_admin() throws Exception {
        mockMvc.perform(post("/voting/edit/{id}", 1).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("index.jsp"));
    }

    @Test
    public void edit_voting_data_noLogInUser() throws Exception {
        mockMvc.perform(post("/voting/edit/{id}", 1).with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void go_to_delete_form_user() throws Exception {
        mockMvc.perform(get("/voting/delete/{id}", 1))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void go_to_delete_form_admin() throws Exception {
        mockMvc.perform(get("/voting/delete/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("deleteVoting.jsp"));
    }

    @Test
    public void go_to_delete_form_noLogInUser() throws Exception {
        mockMvc.perform(get("/voting/delete/{id}", 1))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void delete_voting_user() throws Exception {
        mockMvc.perform(post("/voting/delete/{id}",1).with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void delete_voting_admin() throws Exception {
        mockMvc.perform(post("/voting/delete/{id}",1).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("index.jsp"));
    }

    @Test
    public void delete_voting_noLogInUser() throws Exception {
        mockMvc.perform(post("/voting/delete/{id}",1).with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void go_to_close_form_user() throws Exception {
        mockMvc.perform(get("/voting/close/{id}", 1))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void go_to_close_form_admin() throws Exception {
        mockMvc.perform(get("/voting/close/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("closeVoting.jsp"));
    }

    @Test
    public void go_to_close_form_noLogInUser() throws Exception {
        mockMvc.perform(get("/voting/close/{id}", 1))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void close_voting_user() throws Exception {
        mockMvc.perform(post("/voting/close/{id}", 1).with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void close_voting_admin() throws Exception {
        mockMvc.perform(post("/voting/close/{id}", 1).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("index.jsp"));
    }

    @Test
    public void close_voting_noLogInUser() throws Exception {
        mockMvc.perform(post("/voting/close/{id}", 1).with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    public void go_to_result_form_user_admin() throws Exception{
        mockMvc.perform(get("/voting/result/{id}",1))
                .andExpect(status().isOk())
                .andExpect(view().name("votingResult.jsp"));
    }

    @Test
    public void go_to_result_form_noLogInUser() throws Exception{
        mockMvc.perform(get("/voting/result/{id}",1))
                .andExpect(status().is3xxRedirection());
    }
}
