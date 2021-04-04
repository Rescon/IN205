package ensta.exception;

public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public ServiceException(String message) {
        super(message);
    }
}
