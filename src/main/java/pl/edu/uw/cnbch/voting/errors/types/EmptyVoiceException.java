package pl.edu.uw.cnbch.voting.errors.types;

public class EmptyVoiceException extends RuntimeException{

    public static String ErrorMessage = EmptyVoiceException.class.getSimpleName()
            + ": Głos nie może być pusty!";

}
