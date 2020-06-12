package myServer.httpResponse;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jyami on 2020/04/30
 */
@Slf4j
public class HttpResponseEntity {

    private static final String LINE_SEPERATOR = "\r\n";

    private DataOutputStream dos;
    private ResponseStatusLine responseStatusLine = ResponseStatusLine.create(ResponseStatusLine.HttpStatus.OK);
    private Map<String, String> headers = new HashMap<>();
    private byte[] body = new byte[0];

    private HttpResponseEntity() {
    }

    public HttpResponseEntity(OutputStream out) {
        dos = new DataOutputStream(out);
    }

    public void forward(String bodyContentsUrl) {
        try {
            forwardBody(bodyContentsUrl);
            response200Header(bodyContentsUrl);
            statusLineProcess();
            headerProcess();
            bodyProcess();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void sendRedirect(String redirectUrl) {
        try {
            response302Header(redirectUrl);
            statusLineProcess();
            headerProcess();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    private void response200Header(String contentsBodyUrl) {
        responseStatusLine = ResponseStatusLine.create(ResponseStatusLine.HttpStatus.OK);
        addHeader("Content-Type", "text/" + contentsType(contentsBodyUrl) + ";charset=utf-8");
        addHeader("Content-Length", "" + body.length);
    }

    private void response302Header(String redirectUrl) {
        responseStatusLine = ResponseStatusLine.create(ResponseStatusLine.HttpStatus.FOUND);
        addHeader("Location", redirectUrl);
    }

    private void forwardBody(String bodyContents) throws IOException {
        body = Files.readAllBytes(new File("./webapp" + bodyContents).toPath());
    }

    private void statusLineProcess() throws IOException {
        dos.writeBytes(responseStatusLine.transferStatusFormat() + LINE_SEPERATOR);
    }

    private void headerProcess() throws IOException {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String headerString = String.format("%s: %s " + LINE_SEPERATOR, entry.getKey(), entry.getValue());
            dos.writeBytes(headerString);
        }
        dos.writeBytes("\r\n");
    }

    private void bodyProcess() throws IOException {
        dos.write(body, 0, body.length);
        dos.flush();
    }

    private static String contentsType(String contentsBodyUrl) {
        int extensionIndex = contentsBodyUrl.lastIndexOf(".");
        return contentsBodyUrl.substring(extensionIndex + 1);
    }
}
