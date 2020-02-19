package pl.edu.uw.cnbch.voting.errors.types;

public class PasswordsNotEqualException extends RuntimeException {

    public static String ErrorMessage = PasswordsNotEqualException.class.getSimpleName()
            + ": Podane hasła nie są takie same!";
}
