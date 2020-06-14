package http;

import util.HttpRequestUtils;

public class HttpRequestLine {
    private final HttpMethod httpMethod;
    private final String path;

    public HttpRequestLine(final String httpRequestLine) {
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
