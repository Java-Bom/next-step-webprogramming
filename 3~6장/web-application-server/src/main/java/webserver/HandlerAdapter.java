package webserver;

import http.request.HttpRequest;
import http.response.HttpResponse;
import webserver.handler.ResponseHandler;

import java.io.IOException;

public class HandlerAdapter {
    private final ResponseHandler responseHandler;

    public HandlerAdapter(final ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    public void doService(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        responseHandler.response(httpRequest, httpResponse);
    }
}
