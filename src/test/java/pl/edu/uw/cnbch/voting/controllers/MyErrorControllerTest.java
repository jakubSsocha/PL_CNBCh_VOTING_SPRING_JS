package pl.edu.uw.cnbch.voting.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.uw.cnbch.voting.services.ErrorMessageService;
import pl.edu.uw.cnbch.voting.services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(MyErrorController.class)
public class MyErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ErrorMessageService errorMessageService;

    @MockBean
    private UserService userService;

    @Test
    public void go_to_error_page() throws Exception{
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("error.jsp"));
    }

}
