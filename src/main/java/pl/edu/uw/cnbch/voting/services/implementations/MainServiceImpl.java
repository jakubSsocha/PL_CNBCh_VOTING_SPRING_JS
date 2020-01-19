package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.edu.uw.cnbch.voting.services.MainService;

import java.util.Optional;

@Service
public class MainServiceImpl implements MainService {

    @Override
    public void checkForErrorsIn(BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            throw new Exception("Walidacja nieudana - błędne dane w formularzu");
        }
    }

    @Override
    public boolean checkIfIsEmpty(Optional optional) throws Exception {
        if(!optional.isPresent()){
            throw new Exception("Błąd pobrania informacji z bazy danych");
        }
        return false;
    }

}
