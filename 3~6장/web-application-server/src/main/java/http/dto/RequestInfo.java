package http.dto;

import http.RequestUriContainer;

import java.util.Objects;

public class RequestInfo {
    private RequestUriContainer.Method method;
    private String url;
    private boolean isDefault = true;

    public RequestInfo(final RequestUriContainer.Method method, final String url) {
        this.method = method;
        this.url = url;
    }

    public RequestInfo(final RequestUriContainer.Method method, final String url, final boolean isDefault) {
        this.method = method;
        this.url = url;
        this.isDefault = isDefault;
    }

    public boolean isGet() {
        return this.method == RequestUriContainer.Method.GET;
    }

    public boolean isPost() {
        return this.method == RequestUriContainer.Method.POST;
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


    public boolean enableAccess(String logined) {
        if (isDefault) {
            return true;
        }

        return "true".equals(logined);
    }
}
