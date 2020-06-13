package webserver.container;

import util.HttpRequestUtils;

import java.util.Map;

public class RequestUriContainer {
    private String url;
    private Method method;
    private Map<String, String> queryString;
    private String httpVersion;
    private Map<String, String> cookie;

    public RequestUriContainer(String requestUriString) {
        String[] requestUri = requestUriString.split(" ");

        if (requestUri.length != 3) {
            throw new RuntimeException("uri 정보가 아닙니다.");
        }

        this.method = Method.valueOf(requestUri[0]);
        extractUrl(requestUri[1]);
        this.httpVersion = requestUri[2];


    }

    private void extractUrl(String url) {
        if (!url.contains("?")) {
            this.url = url;
            return;
        }

        int index = url.indexOf("?");
        this.url = url.substring(0, index);
        this.queryString = HttpRequestUtils.parseQueryString(url.substring(index + 1));
    }

    public enum Method {
        GET, POST
    }

    public boolean isPost() {
        return Method.POST == method;
    }

    public String getUrl() {
        return this.url;
    }

    public Method getMethod() {
        return method;
    }



}
