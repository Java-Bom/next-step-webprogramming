package http;

import util.HttpRequestUtils;

import java.util.Map;

public class HttpBody {
    private final String body;
    private final Map<String, String> params;

    public HttpBody(final String body) {
        this.body = body;
        this.params = HttpRequestUtils.parseQueryString(body);
    }

    public String getParameter(final String key) {
        return this.params.get(key);
    }
}
