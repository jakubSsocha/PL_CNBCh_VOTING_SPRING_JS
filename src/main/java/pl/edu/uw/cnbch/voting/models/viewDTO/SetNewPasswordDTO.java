package pl.edu.uw.cnbch.voting.models.viewDTO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SetNewPasswordDTO {

    @NotNull
    @NotEmpty
    @Length(min = 6)
    private String oldPassword;

    @NotNull
    @NotEmpty
    @Length(min = 6)
    private String newPassword;

    @NotNull
    @NotEmpty
    @Length(min = 6)
    private String repeatedNewPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatedNewPassword() {
        return repeatedNewPassword;
    }

    public void setRepeatedNewPassword(String repeatedNewPassword) {
        this.repeatedNewPassword = repeatedNewPassword;
    }
}
