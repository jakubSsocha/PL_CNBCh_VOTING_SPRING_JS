package pl.edu.uw.cnbch.voting.errors.types;

public class AdminDeleteException extends RuntimeException {

    public static String ErrorMessage = AdminDeleteException.class.getSimpleName()
            + ": Nie można usunąć użytkownika który jest Administratorem!";
}
