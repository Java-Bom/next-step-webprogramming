package http;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpResponse {
    private final OutputStream out;
    private final Map<String, String> responseData = new HashMap<>();

    public HttpResponse(final OutputStream out) {
        this.out = out;
    }

    public void forward(final String destination) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeBytes(HttpResponseLine.HTTP_SUCCESS.getDoc());
        Set<String> responseHeaderKey = responseData.keySet();
        for (String key : responseHeaderKey) {
            dos.writeBytes(String.format("%s: %s\r%n", key, responseData.get(key)));
        }
        dos.writeBytes("\r\n");
        byte[] body = Files.readAllBytes(new File("./webapp" + destination).toPath());
//        dos.writeBytes(String.format("%s: %s\r%n","Content-Length", body.length));
        dos.write(body, 0, body.length);
        dos.flush();
    }

    public void forward(final byte[] data) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeBytes(HttpResponseLine.HTTP_SUCCESS.getDoc());
        Set<String> responseHeaderKey = responseData.keySet();
        for (String key : responseHeaderKey) {
            dos.writeBytes(String.format("%s: %s\r%n", key, responseData.get(key)));
        }
        dos.writeBytes("\r\n");
        dos.write(data, 0, data.length);
        dos.flush();
    }

    public void forwordCss(final String destination) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeBytes(HttpResponseLine.HTTP_SUCCESS.getDoc());
        Set<String> responseHeaderKey = responseData.keySet();
        for (String key : responseHeaderKey) {
            dos.writeBytes(String.format("%s: %s\r%n", key, responseData.get(key)));
        }
        dos.writeBytes("\r\n");
        byte[] body = Files.readAllBytes(new File("./webapp" + destination).toPath());
        dos.write(body, 0, body.length);
        dos.flush();
    }

    public void sendRedirect(final String redirectUrl) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeBytes(HttpResponseLine.HTTP_REDIRECT.getDoc());
        Set<String> responseHeaderKey = responseData.keySet();
        for (String key : responseHeaderKey) {
            dos.writeBytes(String.format("%s: %s\r%n", key, responseData.get(key)));
        }
        dos.writeBytes("\r\n");
    }

    public void addHeader(final String key, final String data) {
        this.responseData.put(key, data);
    }
}