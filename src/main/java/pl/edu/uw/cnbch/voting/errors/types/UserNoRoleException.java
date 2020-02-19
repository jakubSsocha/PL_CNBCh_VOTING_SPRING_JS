package pl.edu.uw.cnbch.voting.errors.types;

public class UserNoRoleException extends RuntimeException {

    public static String ErrorMessage = UserNoRoleException.class.getSimpleName()
            + ": Każdy użytkownik musi mieć przynajmniej jedną rolę!";
}
