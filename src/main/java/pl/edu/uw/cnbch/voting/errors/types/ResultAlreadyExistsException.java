package pl.edu.uw.cnbch.voting.errors.types;

public class ResultAlreadyExistsException extends RuntimeException {

    public static String ErrorMessage = LoadFromDatabaseException.class.getSimpleName()
            + ": Rezultat dla podanego głosowania i użytkownika już istniej!";

}
