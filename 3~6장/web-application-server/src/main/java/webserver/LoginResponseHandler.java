package webserver;

import exception.UserNotFoundException;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Function;

public class LoginResponseHandler implements ResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(LoginResponseHandler.class);
    private final Function<User, String> function;

    public LoginResponseHandler(final Function<User, String> function) {
        this.function = function;
    }

    @Override
    public void response(final DataOutputStream dos) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public void response(final DataOutputStream dos, final String bodyString) throws IOException {
        User user = BodyExtractor.extract(User.class, bodyString);

        try {
            String response = this.function.apply(user);
            byte[] body = Files.readAllBytes(new File("./webapp/" + response).toPath());
            response200Header(dos, body.length);
            dos.writeBytes("Set-Cookie: logined=true\r\n");
            dos.writeBytes("\r\n");
            dos.write(body, 0, body.length);
        } catch (UserNotFoundException e) {
            byte[] body = Files.readAllBytes(new File("./webapp/user/login_failed.html").toPath());
            response200Header(dos, body.length);
            dos.writeBytes("Set-Cookie: logined=false\r\n");
            dos.writeBytes("\r\n");
            dos.write(body, 0, body.length);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        dos.flush();
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
