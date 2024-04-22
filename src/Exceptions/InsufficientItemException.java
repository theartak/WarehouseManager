package Exceptions;

public class InsufficientItemException extends Exception {
    public InsufficientItemException(String message) {
        super(message);
    }
}