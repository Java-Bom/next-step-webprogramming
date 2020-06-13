package webserver.handler;

import http.request.HttpRequest;
import http.response.HttpResponse;

import java.io.IOException;

public interface ResponseHandler {

    void response(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException;
}
