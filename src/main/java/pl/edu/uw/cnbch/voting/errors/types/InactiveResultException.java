package pl.edu.uw.cnbch.voting.errors.types;

public class InactiveResultException extends RuntimeException {

    public static String ErrorMessage = InactiveResultException.class.getSimpleName()
            + ": Nie można oddać głosu!";

}
