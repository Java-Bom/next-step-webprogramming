package webserver;

import http.request.HttpRequest;
import http.response.HttpResponse;
import webserver.handler.ResponseHandler;

import java.io.IOException;

public class HandlerAdapter {

    public void doService(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        ResponseHandler responseHandler = HandlerMapping.find(httpRequest);

        responseHandler.response(httpRequest, httpResponse);
    }
}
