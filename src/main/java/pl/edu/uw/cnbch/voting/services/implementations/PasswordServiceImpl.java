package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.viewDTO.SetNewPasswordDTO;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.PasswordService;
import pl.edu.uw.cnbch.voting.services.UserService;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final MainService mainService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    public PasswordServiceImpl(MainService mainService,
                               BCryptPasswordEncoder bCryptPasswordEncoder,
                               UserService userService) {
        this.mainService = mainService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @Override
    public void isPasswordCorrect(String password) throws Exception {
        User user = mainService.getLoggedUser();
        if(!bCryptPasswordEncoder.matches(password,user.getPassword())){
            throw new Exception("Podane hasło do konta jest nieprawidłowe!");
        }
    }

    @Override
    public void setNewPassword(SetNewPasswordDTO setNewPasswordDTO) throws Exception {
        User user = mainService.getLoggedUser();
        isPasswordCorrect(setNewPasswordDTO.getOldPassword());
        checkIfBothPasswordsAreEquals(setNewPasswordDTO.getNewPassword(),
                setNewPasswordDTO.getRepeatedNewPassword());
        user.setPassword(setNewPasswordDTO.getNewPassword());
        userService.saveUserNewPassword(user);
    }

    private void checkIfBothPasswordsAreEquals(String firstPassword,
                                                  String secondPassword) throws Exception{
        if(!firstPassword.equals(secondPassword)){
            throw new Exception("Podane nowe hasła nie są takie same");
        }
    }

    public void resetPassword(){
//TODO - implement mechanism for password reset
    }
}
