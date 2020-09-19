package next.exception;

/**
 * Created by jyami on 2020/09/19
 */
public class CannotDeleteException extends RuntimeException {

    public CannotDeleteException() {
        super();
    }

    public CannotDeleteException(String message) {
        super(message);
    }
}
