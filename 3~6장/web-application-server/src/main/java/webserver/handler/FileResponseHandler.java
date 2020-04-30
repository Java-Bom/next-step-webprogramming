package webserver.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileResponseHandler implements ResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(FileResponseHandler.class);

    private final String fileName;
    private final String extension;

    public FileResponseHandler(final String fileName) {
        this.fileName = fileName;
        String[] split = fileName.split("\\.");
        int lastIndex = split.length - 1;
        this.extension = split[lastIndex];
    }

    @Override
    public void response(final DataOutputStream dos, final String bodyString) throws IOException {
        byte[] body = Files.readAllBytes(new File("./webapp" + fileName).toPath());
        response200Header(dos, body.length);
        dos.write(body, 0, body.length);
        dos.flush();
    }


    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/" + extension + ";charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
