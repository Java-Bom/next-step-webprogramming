package next.model;

public class Result {
    private boolean status;
    private String message;

    public Result(final boolean status, final String message) {
        this.status = status;
        this.message = message;
    }

    public static final Result ok() {
        return new Result(true, null);
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
