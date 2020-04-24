package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;

public class UserResponseHandler implements ResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(IndexResponseHandler.class);

    @Override
    public void response(DataOutputStream dos) throws IOException {
        try {
            dos.writeBytes("HTTP/1.1 302 Found\n \r\n");
            dos.writeBytes("Location: http://localhost:8080/index.html\r\n");
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
