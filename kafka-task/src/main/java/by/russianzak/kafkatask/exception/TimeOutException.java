package by.russianzak.kafkatask.exception;

public class TimeOutException extends RuntimeException {

    public TimeOutException(String message) {
        super(message);
    }

    public TimeOutException(String message, Throwable cause) {
        super(message, cause);
    }
}
