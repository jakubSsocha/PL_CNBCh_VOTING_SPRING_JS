package pl.edu.uw.cnbch.voting.errors.types;

public class NoSystemAdminException extends RuntimeException {

    public static String ErrorMessage = NoSystemAdminException.class.getSimpleName()
            + ": Przynajmniej jeden Administrator musi być aktywny!";
}
