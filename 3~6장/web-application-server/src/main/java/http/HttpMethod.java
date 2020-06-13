package http;

import java.util.Arrays;

public enum HttpMethod {
    GET, POST, NONE;

    public static HttpMethod findByKeyword(final String token) {
        return Arrays.stream(values())
                .filter(httpMethod -> httpMethod.name().equals(token.toUpperCase()))
                .findFirst()
                .orElse(NONE);
    }
}
