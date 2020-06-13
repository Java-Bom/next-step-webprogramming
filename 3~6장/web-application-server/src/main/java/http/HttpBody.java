package http;

import util.HttpRequestUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpBody {
    private final Map<String, String> params;

    public HttpBody(final int contentLength, final String body) {
        if (contentLength > 0) {
            this.params = HttpRequestUtils.parseQueryString(body);
            return;
        }
        ;
        params = new HashMap<>();
    }

    public String getParameter(final String key) {
        return this.params.get(key);
    }
}
