package pl.edu.uw.cnbch.voting.services.implementations;

import org.junit.Test;
import pl.edu.uw.cnbch.voting.errors.types.IncorrectPasswordException;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.PasswordService;

import static org.mockito.Mockito.*;


public class PasswordServiceImplTest {

    private PasswordService passwordService;
    private MainService mainService;

    @Test(expected = IncorrectPasswordException.class)
    public void fail_is_password_correct() throws  Exception{
        passwordService = spy(PasswordServiceImpl.class);
        mainService = mock(MainService.class);
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
