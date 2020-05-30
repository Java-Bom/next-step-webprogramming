package myServer.exception;

/**
 * Created by jyami on 2020/05/30
 */
public class InternalServerException extends RuntimeException {
    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
