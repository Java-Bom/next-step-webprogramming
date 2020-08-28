package next.user;

public class SessionUser {
    private final String userId;

    public SessionUser(final String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
