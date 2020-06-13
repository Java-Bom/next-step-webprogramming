package http.response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ResponseHeaders {
    private final Map<String, String> headers = new HashMap<>();

    public void add(String key, String value) {
        this.headers.put(key, value);
    }

    public void addContentType(String url) {
        add("Content-Type", ContentType.findContentType(url));
    }

    public void response200Header(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            writeHeaders(dos);
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void response302Redirect(DataOutputStream dos) {
        try {
            dos.writeBytes("HTTP/1.1 302 Redirect \r\n");
            writeHeaders(dos);
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeHeaders(DataOutputStream dos) {
        try {
            for (Map.Entry<String, String> keyValue : headers.entrySet()) {
                String key = keyValue.getKey();
                String value = keyValue.getValue();
                dos.writeBytes(key + ": " + value + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private enum ContentType {
        CSS(".css", "text/css"),
        JS(".js", "application/javascript"),
        DEFAULT("", "text/html;charset=utf-8");

        private final String ext;
        private final String contentType;

        ContentType(final String ext, final String contentType) {
            this.ext = ext;
            this.contentType = contentType;
        }

        private static String findContentType(String page) {
            return Arrays.stream(values())
                    .filter(contentType -> page.endsWith(contentType.ext))
                    .findFirst()
                    .orElse(DEFAULT)
                    .contentType;
        }
    }

}
