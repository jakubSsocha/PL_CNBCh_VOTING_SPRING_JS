package pl.edu.uw.cnbch.voting.errors.types;

public class LoadFromDatabaseException extends RuntimeException {

    public static String ErrorMessage = LoadFromDatabaseException.class.getSimpleName()
            + ": Nie znaleziono obiektu w bazie danych!";

}
