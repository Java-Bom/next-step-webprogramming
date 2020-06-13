package http.request;

import util.HttpRequestUtils;

import java.util.HashMap;
import java.util.Map;

public class RequestParams {
    private Map<String, String> params = new HashMap<>();

    public RequestParams(final HttpMethod method, final RequestUri requestUri, final String body) {
        if (method.isPost()) {
            this.params = HttpRequestUtils.parseQueryString(body);
            return;
        }
        requestUri.getQueryParams()
                .ifPresent(params -> this.params.putAll(HttpRequestUtils.parseQueryString(params)));
    }

    public String get(final String key) {
        return this.params.get(key);
    }

}