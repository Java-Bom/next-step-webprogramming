package webserver.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CssResponseHandler implements ResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(CssResponseHandler.class);

    private final String cssName;

    public CssResponseHandler(final String cssName) {
        this.cssName = cssName;
    }

    @Override
    public void response(final DataOutputStream dos, final String bodyString) throws IOException {
        byte[] body = Files.readAllBytes(new File("./webapp/" + cssName).toPath());
        response200Header(dos, body);
        dos.write(body, 0, body.length);
        dos.flush();
    }

    private void response200Header(DataOutputStream dos, byte[] body) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/css;charset=utf-8\r\n");
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
