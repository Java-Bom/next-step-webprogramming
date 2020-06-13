package http.request;

import util.HttpRequestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class RequestHeaders {
    private final Map<String, String> headers;

    public RequestHeaders(final List<String> headers) {
        this.headers = headers.stream()
                .map(HttpRequestUtils::parseHeader)
                .collect(toMap(HttpRequestUtils.Pair::getKey, HttpRequestUtils.Pair::getValue));
    }

    public int getContentLength() {
        return Integer.parseInt(headers.get("Content-Length"));
    }

    public String get(final String name) {
        return this.headers.get(name);
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }
}