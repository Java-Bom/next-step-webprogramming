package http.request;

import http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;

public class HttpRequestLine {
    private static final Logger log = LoggerFactory.getLogger(HttpRequestLine.class);
    private final HttpMethod httpMethod;
    private final String path;

    public HttpRequestLine(final String httpRequestLine) {
        log.info("HttpRequestLine: {}", httpRequestLine);
        this.httpMethod = HttpRequestUtils.extractHttpMethod(httpRequestLine);
        this.path = HttpRequestUtils.extractUrlPath(httpRequestLine);
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }
}
