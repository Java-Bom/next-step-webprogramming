package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.function.Supplier;

public class UserFindResponseHandler implements ResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(LoginResponseHandler.class);
    private final Supplier<String> supplier;

    public UserFindResponseHandler(final Supplier<String> supplier) {
        this.supplier = supplier;
    }

    @Override
    public void response(final DataOutputStream dos) throws IOException {
        String responseText = supplier.get();
        byte[] body = responseText.getBytes();
        response200Header(dos, body);
    }

    @Override
    public void response(final DataOutputStream dos, final String bodyString) throws IOException {
        throw new RuntimeException();
    }

    private void response200Header(DataOutputStream dos, byte[] body) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + body.length + "\r\n");
            dos.writeBytes("\r\n");
            dos.write(body, 0, body.length);
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
