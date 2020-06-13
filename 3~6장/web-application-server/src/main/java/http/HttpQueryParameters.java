package http;

import util.HttpRequestUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpQueryParameters {
    private static final String QUERY_KEYWORD = "?";
    private final Map<String, String> params;

    public HttpQueryParameters(final String path) {
        if (!path.contains(QUERY_KEYWORD)) {
            params = new HashMap<>();
            return;
        }
        this.params = HttpRequestUtils.parseQueryString(getQueryString(path));
    }

    private String getQueryString(final String path) {
        return path.substring(path.indexOf(QUERY_KEYWORD) + 1);
    }

    public String getQueryParam(final String key) {
        return this.params.get(key);
    }
}
