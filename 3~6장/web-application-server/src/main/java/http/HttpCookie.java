package http;

import util.HttpRequestUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpCookie {
    private final Map<String, String> cookies = new HashMap<>();

    public HttpCookie() {
    }

    public HttpCookie(final Map<String, String> headers) {
        headers.keySet().stream()
                .filter("Cookie"::equals)
                .findFirst()
                .ifPresent(key -> cookies.putAll(HttpRequestUtils.parseCookies(headers.get(key))));
    }

    public String get(final String key) {
        return cookies.get(key);
    }

//    public String convertHeader() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Cookie: ");
//        for (String key : cookies.keySet()) {
//            String value = cookies.get(key);
//            sb.append(key)
//                    .append("=")
//                    .append(value)
//                    .append("; ");
//        }
//        return sb.toString();
//    }

    public void add(final String key, final String value) {
        this.cookies.put(key, value);
    }
}
