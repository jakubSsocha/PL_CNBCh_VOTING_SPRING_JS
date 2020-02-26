package pl.edu.uw.cnbch.voting.services.implementations;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BindingResult;
import pl.edu.uw.cnbch.voting.errors.types.UnsuccessfulValidationException;
import pl.edu.uw.cnbch.voting.repositories.UserRepository;
import pl.edu.uw.cnbch.voting.services.MainService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainServiceImplTest {

    private MainService mainService;
    private BindingResult bindingResult;

    @Before
    public void before(){
        mainService = new MainServiceImpl(mock(UserRepository.class));
        bindingResult = mock(BindingResult.class);
    }

    @Test(expected = UnsuccessfulValidationException.class)
    public void fail_check_for_errors_in() throws Exception {
        when(bindingResult.hasErrors()).thenReturn(true);

        mainService.checkForErrorsIn(bindingResult);
    }

}
