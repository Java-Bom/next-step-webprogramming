package http.response;

import http.ContentType;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpResponse {
    private final DataOutputStream dos;
    private final Map<String, String> header = new HashMap<>();

    public HttpResponse(final OutputStream out) {
        this.dos = new DataOutputStream(out);
    }

    public void forward(final String url) throws IOException {
        byte[] body = Files.readAllBytes(new File("./webapp" + url).toPath());
        header.put("Content-Type", ContentType.findMarkByUrl(url));
        header.put("Content-Length", body.length + "");
        response200Header();
        responseBody(body);
    }

    public void forwardBody(final String body) throws IOException {
        byte[] contents = body.getBytes();
        header.put("Content-Type", ContentType.HTML.getValue());
        header.put("Content-Length", contents.length + "");
        response200Header();
        responseBody(contents);
    }

    private void response200Header() throws IOException {
        dos.writeBytes(HttpResponseLine.HTTP_SUCCESS.getDoc());
        processHeaders();
        dos.writeBytes("\r\n");
    }

    private void responseBody(final byte[] body) throws IOException {
        dos.write(body, 0, body.length);
        dos.writeBytes("\r\n");
        dos.flush();
    }


    public void sendRedirect(final String redirectUrl) throws IOException {
        dos.writeBytes(HttpResponseLine.HTTP_REDIRECT.getDoc());
        processHeaders();
        dos.writeBytes("Location: " + redirectUrl + " \r\n");
        dos.writeBytes("\r\n");
    }


    private void processHeaders() throws IOException {
        Set<String> responseHeaderKey = header.keySet();
        for (String key : responseHeaderKey) {
            dos.writeBytes(String.format("%s: %s\r%n", key, header.get(key)));
        }
    }

    public void addHeader(final String key, final String value) {
        this.header.put(key, value);
    }
}