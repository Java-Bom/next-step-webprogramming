package http;

import java.util.Arrays;

public enum HttpHeader {
    HOST("Host"), CONNECTION("Connection"), ACCEPT("Accept"), CONTENT_LENGTH("Content-Length"),
    CONTENT_TYPE("Content-Type"), COOKIE("Cookie"), SET_COOKIE("Set-Cookie"), NONE("NONE");

    private static final String VALUE_DELIMITER = ":";
    private final String name;

    HttpHeader(final String name) {
        this.name = name;
    }

    public static HttpHeader findByRequest(final String line) {
        return Arrays.stream(values())
                .filter(httpHeader -> line.contains(httpHeader.name))
                .findFirst()
                .orElse(NONE);
    }
}
