package pl.edu.uw.cnbch.voting.errors.types;

public class VotingInactiveException extends RuntimeException {

    public static String ErrorMessage = VotingInactiveException.class.getSimpleName()
            + ": Głosowanie usunięte - nie można go modyfikować!";
}
