package pl.edu.uw.cnbch.voting.errors.types;

public class UnsuccessfulValidationException extends RuntimeException {

    public static String ErrorMessage = UnsuccessfulValidationException.class.getSimpleName()
            + ": Rezultat dla podanego głosowania i użytkownika już istniej!";
}
