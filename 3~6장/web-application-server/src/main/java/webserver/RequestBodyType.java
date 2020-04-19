package webserver;

import model.User;

public enum RequestBodyType {
    USER("/user/create", User.class);

    private final String path;
    private final Class<?> type;

    RequestBodyType(final String path, final Class<?> type) {
        this.path = path;
        this.type = type;
    }

    public static RequestBodyType findByUrl(final String url) {
        return null;
    }

    public String getPath() {
        return path;
    }

    public Class<?> getType() {
        return type;
    }

    public <T> void checkType(final Class<T> tClass) {
        if (!this.type.equals(tClass)) {
            throw new RuntimeException();
        }
    }
}
