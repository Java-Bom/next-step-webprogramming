package http.request;

import util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestBody {
    private String body;

    public RequestBody(BufferedReader br, int contentLength) throws IOException {
        this.body = IOUtils.readData(br, contentLength);
    }

    public String getBody() {
        return body;
    }
}

