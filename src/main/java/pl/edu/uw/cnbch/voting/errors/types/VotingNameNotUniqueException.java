package pl.edu.uw.cnbch.voting.errors.types;

public class VotingNameNotUniqueException extends RuntimeException{

    public static String ErrorMessage = VotingNameNotUniqueException.class.getSimpleName()
            + ": Głosowanie o podanej nazwie już istnieje!";
}
