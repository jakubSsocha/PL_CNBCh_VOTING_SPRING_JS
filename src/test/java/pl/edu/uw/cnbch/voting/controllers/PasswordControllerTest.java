package pl.edu.uw.cnbch.voting.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.uw.cnbch.voting.errors.ErrorManager;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.PasswordService;
import pl.edu.uw.cnbch.voting.services.SuccessMessageService;
import pl.edu.uw.cnbch.voting.services.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PasswordController.class)
public class PasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasswordService passwordService;

    @MockBean
    private MainService mainService;

    @MockBean
    private SuccessMessageService successMessageService;

    @MockBean
    private ErrorManager errorManager;

    @MockBean
    private UserService userService;

    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    public void go_to_set_new_password_page() throws Exception {
        mockMvc.perform(get("/password/setNew"))
                .andExpect(status().isOk())
                .andExpect(view().name("setNewPassword.jsp"));
    }

    @Test
    public void go_to_set_new_password_page_noLogInUser() throws Exception {
        mockMvc.perform(get("/password/setNew"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(roles = {"USER", "ADMIN"})
    @Test
    public void post_set_new_password_page() throws Exception {
        mockMvc.perform(post("/password/setNew").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("index.jsp"));
    }

    @Test
    public void post_set_new_password_page_noLogInUser() throws Exception {
        mockMvc.perform(post("/password/setNew").with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

}
