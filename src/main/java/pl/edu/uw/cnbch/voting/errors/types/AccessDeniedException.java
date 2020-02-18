package pl.edu.uw.cnbch.voting.errors.types;

public class AccessDeniedException extends RuntimeException{

    public static String ErrorMessage = AccessDeniedException.class.getSimpleName() + ": Odmowa dostępu!";

}
