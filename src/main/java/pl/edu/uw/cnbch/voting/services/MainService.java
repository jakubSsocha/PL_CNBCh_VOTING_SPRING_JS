package pl.edu.uw.cnbch.voting.services;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import pl.edu.uw.cnbch.voting.models.entities.User;

import java.util.Optional;

public interface MainService <T> {

    void checkForErrorsIn(BindingResult bindingResult) throws Exception;

    boolean checkIfOptionalIsEmpty(Optional<T> optional) throws Exception;

    User getLoggedUser() throws Exception;

}
