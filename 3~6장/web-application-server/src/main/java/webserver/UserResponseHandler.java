package webserver;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.function.Function;

public class UserResponseHandler implements ResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(IndexResponseHandler.class);
    private final Function<User, String> function;

    public UserResponseHandler(final Function<User, String> function) {
        this.function = function;
    }

    @Override
    public void response(DataOutputStream dos) throws IOException {
        throw new RuntimeException();
    }

    @Override
    public void response(final DataOutputStream dos, final String bodyString) throws IOException {
        User user = BodyExtractor.extract(User.class, bodyString);
        String response = this.function.apply(user);

        try {
            dos.writeBytes("HTTP/1.1 302 Found\n \r\n");
            dos.writeBytes("Location: http://localhost:8080/" + response + "\r\n");
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
