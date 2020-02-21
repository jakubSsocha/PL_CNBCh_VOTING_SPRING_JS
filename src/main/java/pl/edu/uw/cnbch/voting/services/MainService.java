package pl.edu.uw.cnbch.voting.services;

import org.springframework.validation.BindingResult;
import pl.edu.uw.cnbch.voting.models.entities.User;


public interface MainService {

    void checkForErrorsIn(BindingResult bindingResult) throws Exception;

    User getLoggedUser() throws Exception;

}
