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

    private static final String LINE_SEPARATOR = "\r\n";

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
            getFileBody(bodyContentsUrl);
            response200Header(bodyContentsUrl);
            statusLineProcess();
            headerProcess();
            bodyProcess();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }


    public void forwardBody(String bodyContent) {
        body = bodyContent.getBytes();
        try {
            response200Header("");
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
        addHeader("Content-Type", getHeaderContentType(contentsBodyUrl));
        addHeader("Content-Length", "" + body.length);
    }

    private void response302Header(String redirectUrl) {
        responseStatusLine = ResponseStatusLine.create(ResponseStatusLine.HttpStatus.FOUND);
        addHeader("Location", redirectUrl);
    }

    private String getHeaderContentType(String contentsBodyUrl) {
        if (contentsBodyUrl.endsWith(".css"))
            return "text/css";
        else if (contentsBodyUrl.endsWith(".js"))
            return "application/javascript";
        else
            return "text/html;charset=utf-8";
    }

    private void getFileBody(String bodyContents) throws IOException {
        body = Files.readAllBytes(new File("./webapp" + bodyContents).toPath());
    }

    private void statusLineProcess() throws IOException {
        dos.writeBytes(responseStatusLine.transferStatusFormat() + LINE_SEPARATOR);
    }

    private void headerProcess() throws IOException {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String headerString = String.format("%s: %s " + LINE_SEPARATOR, entry.getKey(), entry.getValue());
            dos.writeBytes(headerString);
        }
        dos.writeBytes("\r\n");
    }

    private void bodyProcess() throws IOException {
        dos.write(body, 0, body.length);
        dos.flush();
    }
}
