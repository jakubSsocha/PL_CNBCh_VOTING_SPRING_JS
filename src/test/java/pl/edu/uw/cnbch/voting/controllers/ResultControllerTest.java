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
import pl.edu.uw.cnbch.voting.services.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(ResultController.class)
public class ResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResultService resultService;

    @MockBean
    private MainService mainService;

    @MockBean
    private SuccessMessageService successMessageService;

    @MockBean
    private UserService userService;

    @MockBean
    private ErrorMessageService errorMessageService;

    @WithMockUser(roles = "USER")
    @Test
    public void get_jsonFormat_result_user() throws Exception {
        mockMvc.perform(get("/result/{id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    public void get_jsonFormat_result_admin() throws Exception {
        mockMvc.perform(get("/result/{id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void get_jsonFormat_result_noLogInUser() throws Exception {
        mockMvc.perform(get("/result/{id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void go_to_voting_page_user() throws Exception {
        mockMvc.perform(get("/result/vote/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("vote.jsp"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void go_to_voting_page_admin() throws Exception {
        mockMvc.perform(get("/result/vote/{id}", 1))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void go_to_voting_page_noLogInUser() throws Exception {
        mockMvc.perform(get("/result/vote/{id}", 1))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = "USER")
    @Test
    public void post_create_voting_result_user() throws Exception{
        mockMvc.perform(post("/result/vote/1").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("index.jsp"));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void post_create_voting_result_admin() throws Exception {
        mockMvc.perform(post("/result/vote/{id}", 1).with(csrf()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void post_create_voting_result_noLogInUser() throws Exception{
        mockMvc.perform(post("/result/vote/{id}", 1).with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

}
