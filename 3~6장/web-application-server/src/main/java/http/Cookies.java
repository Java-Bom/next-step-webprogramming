package http;

import java.util.HashMap;
import java.util.Map;

public class Cookies {
    private final Map<String, Object> cookies = new HashMap<>();

    public void addCookies(final Map<String, String> cookies) {
        this.cookies.putAll(cookies);
    }

    public Object getCookie(final String key) {
        return cookies.get(key);
    }
}
