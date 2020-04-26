package webserver;

import java.util.Objects;

public class RequestInfo {
    private RequestUriContainer.Method method;
    private String url;
    private boolean isDefault = true;
    private String logined;

    public RequestInfo(final RequestUriContainer.Method method, final String url, final String logined) {
        this.method = method;
        this.url = url;
        this.logined = logined;
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

        boolean required = method == that.method &&
                Objects.equals(url, that.url);

        if (isDefault) {
            return required;
        }
        return required &&
                Objects.equals(logined, that.logined);
    }

    @Override
    public int hashCode() {
        if (isDefault) {
            return Objects.hash(method, url);
        }
        return Objects.hash(method, url, logined);
    }

}
