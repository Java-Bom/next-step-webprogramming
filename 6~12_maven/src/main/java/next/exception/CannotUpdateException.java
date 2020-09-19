package next.exception;

/**
 * Created by jyami on 2020/09/19
 */
public class CannotUpdateException extends RuntimeException {

    public CannotUpdateException() {
    }

    public CannotUpdateException(String message) {
        super(message);
    }
}

