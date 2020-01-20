package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.repositories.UserRepository;
import pl.edu.uw.cnbch.voting.services.MainService;

import java.util.Optional;

@Service
public class MainServiceImpl implements MainService {

    private final UserRepository userRepository;

    public MainServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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

    @Override
    public User getLoggedUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user = userRepository.findByUsername(currentUserName);
            return user;
        } else {
            throw new Exception("Błąd bazy danych");
        }
    }

}
