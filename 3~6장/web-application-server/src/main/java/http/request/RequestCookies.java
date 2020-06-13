package http.request;

import util.HttpRequestUtils;

import java.util.HashMap;
import java.util.Map;

public class RequestCookies {
    private final Map<String, String> cookies = new HashMap<>();

    public RequestCookies(final Map<String, String> headers) {
        headers.keySet().stream()
                .filter("Cookie"::equals)
                .findFirst()
                .ifPresent(key -> cookies.putAll(HttpRequestUtils.parseCookies(headers.get(key))));
    }

    public String get(final String key) {
        return cookies.get(key);
    }
}
