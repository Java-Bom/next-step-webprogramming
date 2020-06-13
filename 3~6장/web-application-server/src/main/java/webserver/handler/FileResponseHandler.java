package webserver.handler;

import http.request.HttpRequest;
import http.response.HttpResponse;

import java.io.IOException;

public class FileResponseHandler implements ResponseHandler {

    private final String fileName;

    public FileResponseHandler(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void response(final HttpRequest httpRequest, final HttpResponse httpResponse) throws IOException {
        httpResponse.forward(fileName);
    }

}
