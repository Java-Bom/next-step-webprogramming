package http.request;

import util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestBody {
    private String body;

    public RequestBody(final RequestUri requestUri, BufferedReader br, int contentLength) throws IOException {
        if (requestUri.isPost()) {
            this.body = IOUtils.readData(br, contentLength);
        }
    }

    public String getBody() {
        return body;
    }
}

