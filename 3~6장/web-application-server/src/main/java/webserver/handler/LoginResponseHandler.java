package webserver.handler;

import domain.model.User;
import http.request.HttpRequest;
import http.response.HttpResponse;
import webserver.exception.UserNotFoundException;
import webserver.extractor.BodyExtractor;

import java.util.function.Function;

public class LoginResponseHandler implements ResponseHandler {
    private final Function<User, String> function;

    public LoginResponseHandler(final Function<User, String> function) {
        this.function = function;
    }


    @Override
    public void response(final HttpRequest httpRequest, final HttpResponse httpResponse) {
        User user = BodyExtractor.extract(User.class, httpRequest.getBodyString());

        try {
            String response = this.function.apply(user);
            httpRequest.getSession()
                    .setAttribute("user", user);
            httpResponse.forward("/" + response);
        } catch (UserNotFoundException e) {
            httpResponse.forward("/user/login_failed.html");
        }
    }
}
