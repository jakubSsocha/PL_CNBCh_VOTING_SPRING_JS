package pl.edu.uw.cnbch.voting.services;

import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface MainService <T> {

    void checkForErrorsIn(BindingResult bindingResult) throws Exception;

    boolean checkIfIsEmpty(Optional<T> optional) throws Exception;

}
