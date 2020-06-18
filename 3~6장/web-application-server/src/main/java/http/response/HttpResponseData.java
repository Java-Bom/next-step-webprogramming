package http.response;

import java.util.Map;

public class HttpResponseData {
    private final Map<String, String> data;

    public HttpResponseData(final Map<String, String> data) {
        this.data = data;
    }
}
