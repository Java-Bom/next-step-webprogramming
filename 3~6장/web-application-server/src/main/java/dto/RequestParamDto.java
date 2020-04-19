package dto;

import util.HttpRequestUtils;

import java.util.Map;

public class RequestParamDto {
    private String requestPath;
    private String params;

    public RequestParamDto(final String requestPath, final String params) {
        this.requestPath = requestPath;
        this.params = params;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public String getParams() {
        return params;
    }

    public Map<String, String> toMap() {
        return HttpRequestUtils.parseQueryString(params);
    }
}
