package http.request;

import java.util.Optional;

public class RequestUri {
    public static final String URI_PARAM_TOKEN = "\\?";

    private final String[] uris;
    private final String path;

    public RequestUri(String requestUri) {
        this.uris = requestUri.split(" ");
        if (uris.length != 3) {
            throw new IllegalArgumentException("형식에 맞지 않는 URI");
        }

        String[] paths = uris[1].split(URI_PARAM_TOKEN);
        this.path = paths[0];
    }

    public boolean isPost() {
        return HttpMethod.valueOf(uris[0]).isPost();
    }

    public Optional<String> getQueryParams() {
        String[] urls = uris[1].split(URI_PARAM_TOKEN);
        if (urls.length > 1) {
            return Optional.of(urls[1]);
        }
        return Optional.empty();
    }

    public String getMethod() {
        return uris[0];
    }

    public String getPath() {
        return this.path;
    }

}