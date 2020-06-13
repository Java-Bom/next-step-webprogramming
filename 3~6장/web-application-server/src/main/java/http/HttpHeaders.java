package http;

import util.HttpRequestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HttpHeaders {
    private static final String HTTP_HEADER_DELIMITER = ":";
    private final Map<HttpHeader, Object> headers = new HashMap<>();
    private final Cookies cookies = new Cookies();

    public HttpHeaders(final List<String> headers) {
        headers.forEach(this::addHeader);
    }

    public void addHeader(final String headerStr) {
        if (headerStr.equals("")) {
            return;
        }
        HttpHeader httpHeader = HttpHeader.findByRequest(headerStr);
        if (httpHeader == HttpHeader.COOKIE) {
            this.cookies.addCookies(HttpRequestUtils.parseCookies(parseValue(headerStr)));
            return;
        }
        this.headers.put(httpHeader, parseValue(headerStr));
    }

    private String parseValue(final String line) {
        return line.split(HTTP_HEADER_DELIMITER)[1].trim();
    }

    public Optional<Object> getHeader(final HttpHeader key) {
        return Optional.ofNullable(headers.get(key));
    }
}
