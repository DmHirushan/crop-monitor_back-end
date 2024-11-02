package lk.ijse.crop_monitor.exception;

public class DuplicateLicensePlateException extends RuntimeException{
    public DuplicateLicensePlateException() {
        super();
    }

    public DuplicateLicensePlateException(String message) {
        super(message);
    }

    public DuplicateLicensePlateException(String message, Throwable cause) {
        super(message, cause);
    }
}
