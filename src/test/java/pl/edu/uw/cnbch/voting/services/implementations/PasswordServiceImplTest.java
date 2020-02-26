package pl.edu.uw.cnbch.voting.services.implementations;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.uw.cnbch.voting.errors.types.IncorrectPasswordException;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.PasswordService;
import pl.edu.uw.cnbch.voting.services.UserService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PasswordServiceImplTest {

    private PasswordService passwordService;
    private MainService mainService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;

    @Before
    public void before(){
        passwordService = new PasswordServiceImpl(mainService, bCryptPasswordEncoder, userService);
        mainService = mock(MainServiceImpl.class);
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        userService = mock(UserServiceImpl.class);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void fail_is_password_correct() throws  Exception{
        User user = mock(User.class);
        when(mainService.getLoggedUser()).thenReturn(user);
        when(user.getPassword()).thenReturn("password");
        String testPassword = "haslo";

        passwordService.isPasswordCorrect(testPassword);
    }

    @Test
    public void setNewPassword() {
    }
}
