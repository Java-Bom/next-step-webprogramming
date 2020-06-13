package webserver.handler;

import http.request.HttpRequest;
import http.response.HttpResponse;

public class IndexResponseHandler implements ResponseHandler {
    private final String htmlName;

    public IndexResponseHandler(String htmlName) {
        this.htmlName = htmlName;
    }

    @Override
    public void response(final HttpRequest httpRequest, final HttpResponse httpResponse) {
        httpResponse.forward(htmlName);
    }

}
