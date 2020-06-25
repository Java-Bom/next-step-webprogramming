package controller.user;

import controller.AbstractController;
import db.DataBase;
import http.request.HttpRequest;
import http.response.HttpResponse;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

public class UserListController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(UserListController.class);

    @Override
    protected void doGet(final HttpRequest httpRequest, final HttpResponse httpResponse) throws IOException {
        log.info("------------ Call UserListController ------------");
        if (!isLogined(httpRequest)) {
            httpResponse.forward("/user/login.html");
            return;
        }
        Collection<User> users = DataBase.findAll();
        StringBuilder result = new StringBuilder();
        for (User user : users) {
            result.append(user);
            result.append("\n");
        }
        httpResponse.forwardBody(result.toString());
    }

    private boolean isLogined(HttpRequest httpRequest) {
        String result = httpRequest.getCookie("logined").toString();
        return !Objects.isNull(result) && result.equals("true");
    }
}
