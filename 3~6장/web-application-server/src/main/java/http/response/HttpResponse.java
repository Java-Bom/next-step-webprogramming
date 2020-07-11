package http.response;

import http.HttpCookies;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class HttpResponse {

    private final DataOutputStream dos;
    private final ResponseHeaders responseHeaders;
    private final HttpCookies httpCookies = new HttpCookies();

    public HttpResponse(final OutputStream outputStream) {
        this.dos = new DataOutputStream(outputStream);
        this.responseHeaders = new ResponseHeaders();
    }

    public void forward(final String url) {
        try {
            byte[] body = Files.readAllBytes(new File("./webapp" + url).toPath());
            this.responseHeaders.addContentType(url);
            this.responseHeaders.add("Content-Length", body.length + "");
            this.responseHeaders.response200Header(dos);
            responseBody(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forwardWithBody(final String response) {
        byte[] body = response.getBytes();
        this.responseHeaders.add("Content-Length", body.length + "");
        this.responseHeaders.response200Header(dos);
        responseBody(body);
    }

    public void sendRedirect(String url) {
        this.responseHeaders.add("Location", url);
        this.responseHeaders.response302Redirect(dos);
    }

    private void responseBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addHeader(final String key, final String value) {
        this.responseHeaders.add(key, value);
    }

    public void addCookie(String key, String value) {
        httpCookies.add(key, value);
    }
}
