package pl.edu.uw.cnbch.voting.errors.types;

public class VotingClosedException extends RuntimeException {

    public static String ErrorMessage = VotingClosedException.class.getSimpleName()
            + ": Głosowanie zamknięte - nie można go modyfikować!";
}
