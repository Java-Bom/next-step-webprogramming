package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class IndexResponseHandler implements ResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(IndexResponseHandler.class);
    private final String htmlName;

    public IndexResponseHandler(String htmlName) {
        this.htmlName = htmlName;
    }

    @Override
    public void response(DataOutputStream dos) throws IOException {
        byte[] body = Files.readAllBytes(new File("./webapp/" + htmlName).toPath());
        response200Header(dos, body.length);
        dos.write(body, 0, body.length);
        dos.flush();
    }

    @Override
    public void response(final DataOutputStream dos, final String bodyString) throws IOException {
        throw new RuntimeException();
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
