package pl.edu.uw.cnbch.voting.errors.types;

public class IncorrectPasswordException extends RuntimeException {

    public static String ErrorMessage = IncorrectPasswordException.class.getSimpleName()
            + ": Błędne hasło!";
}
