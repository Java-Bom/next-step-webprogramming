package http.request;

import util.HttpRequestUtils;

import java.util.HashMap;
import java.util.Map;

public class RequestParams {
    private Map<String, String> params = new HashMap<>();

    public RequestParams(final HttpMethod method, final RequestUri requestUri, final RequestBody requestBody) {
        if (method.isPost()) {
            this.params = HttpRequestUtils.parseQueryString(requestBody.getBody());
        }
        requestUri.getQueryParams()
                .ifPresent(params -> this.params.putAll(HttpRequestUtils.parseQueryString(params)));
    }

    public String get(final String key) {
        return this.params.get(key);
    }

}