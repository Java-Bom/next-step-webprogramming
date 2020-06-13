package http;

import util.HttpRequestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpHeaders {
    private static final String HTTP_HEADER_DELIMITER = ":";

    private final Map<HttpHeader, String> headers = new HashMap<>();
    private final Cookies cookies = new Cookies();

    public HttpHeaders(final List<String> headers) {
        headers.forEach(this::addHeader);
    }

    public void addHeader(final String headerStr) {
        String optimizeHeader = headerStr.trim();
        if (optimizeHeader.equals("")) {
            return;
        }
        HttpHeader httpHeader = HttpHeader.findByRequest(optimizeHeader);
        if (httpHeader == HttpHeader.COOKIE) {
            this.cookies.addCookies(HttpRequestUtils.parseCookies(parseValue(headerStr)));
            return;
        }
        this.headers.put(httpHeader, parseValue(optimizeHeader));
    }

    private String parseValue(final String line) {
        return line.split(HTTP_HEADER_DELIMITER)[1].trim();
    }

    public String getHeader(final HttpHeader key) {
        return headers.get(key);
    }
}
