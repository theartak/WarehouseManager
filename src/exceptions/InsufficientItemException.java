package exceptions;

public class InsufficientItemException extends Exception {
    public InsufficientItemException(String message) {
        super(message);
    }
}