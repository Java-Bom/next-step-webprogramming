package http.request;

import util.HttpRequestUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpQueryParameters {
    private static final String QUERY_DELIMITER = "?";
    private final Map<String, String> queries;

    public HttpQueryParameters(final String path) {
        if (!path.contains(QUERY_DELIMITER)) {
            queries = new HashMap<>();
            return;
        }
        this.queries = HttpRequestUtils.parseQueryString(getQueryString(path));
    }

    private String getQueryString(final String path) {
        return path.substring(path.indexOf(QUERY_DELIMITER) + 1);
    }

    public String getQueryParam(final String key) {
        return this.queries.get(key);
    }
}
