package next.http;

import java.util.Objects;

/**
 * Created by jyami on 2020/08/27
 */
public class HttpRequest {
    private String url;
    private HttpMethod httpMethod;

    public HttpRequest(String url, HttpMethod httpMethod) {
        this.url = url;
        this.httpMethod = httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HttpRequest that = (HttpRequest) o;
        return Objects.equals(url, that.url) &&
                httpMethod == that.httpMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, httpMethod);
    }
}
