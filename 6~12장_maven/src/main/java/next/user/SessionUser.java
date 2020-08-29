package next.user;

public class SessionUser {
    private final String userId;
    private final String email;

    public SessionUser(final String userId, final String email) {
        this.userId = userId;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public boolean isNotSameUserId(final String userId) {
        return !this.userId.equals(userId);
    }
}
