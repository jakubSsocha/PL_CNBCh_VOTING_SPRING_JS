package pl.edu.uw.cnbch.voting.services;

import pl.edu.uw.cnbch.voting.models.viewDTO.SetNewPasswordDTO;

public interface PasswordService {

    void isPasswordCorrect(String password) throws Exception;

    void setNewPassword(SetNewPasswordDTO setNewPasswordDTO) throws Exception;

    void resetPassword();
}
