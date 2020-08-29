package webserver.dto;

import http.request.HttpMethod;

import java.util.Objects;

public class RequestInfo {
    private HttpMethod method;
    private String url;
    private boolean isDefault = true;

    public RequestInfo(final HttpMethod method, final String url) {
        this.method = method;
        this.url = url;
    }

    public RequestInfo(final HttpMethod method, final String url, final boolean isDefault) {
        this.method = method;
        this.url = url;
        this.isDefault = isDefault;
    }

    public String getUrl() {
        return url;
    }

    public boolean isGet() {
        return this.method.isGet();
    }

    public boolean isPost() {
        return this.method.isPost();
    }


    public boolean enableAccess(boolean logined) {
        if (isDefault) {
            return true;
        }

        return logined;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RequestInfo that = (RequestInfo) o;
        return method == that.method &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, url);
    }

}
