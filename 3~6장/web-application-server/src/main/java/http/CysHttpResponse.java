package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import exception.UserNotFoundException;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class CysHttpResponse {
    private static final Logger log = LoggerFactory.getLogger(CysHttpResponse.class);

    private DataOutputStream dos;

    public CysHttpResponse(OutputStream out) {
        this.dos = new DataOutputStream(out);
    }

    public void response200Header(byte[] body) {
        response200Header(body,"html");
    }

    public void responseRedirect(final String page) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found\n \r\n");
            dos.writeBytes("Location: http://localhost:8080/" + page + "\r\n");
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void response(final String response) throws IOException {
        try {
            byte[] body = Files.readAllBytes(new File("./webapp/" + response).toPath());
            response200Header(body);
            dos.writeBytes("Set-Cookie: logined=true\r\n");
            dos.writeBytes("\r\n");
            dos.write(body, 0, body.length);
        } catch (UserNotFoundException e) {
            byte[] body = Files.readAllBytes(new File("./webapp/user/login_failed.html").toPath());
            response200Header(body);
            dos.writeBytes("Set-Cookie: logined=false\r\n");
            dos.writeBytes("\r\n");
            dos.write(body, 0, body.length);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        dos.flush();
    }


    public void response200Header(byte[] body, String extension) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/" + extension + ";charset=utf-8\r\n");
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
