package controller.user;

import controller.AbstractController;
import db.DataBase;
import http.request.HttpRequest;
import http.response.HttpResponse;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UserCreateController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(UserCreateController.class);

    @Override
    public void doPost(final HttpRequest httpRequest, final HttpResponse httpResponse) throws IOException {
        log.info("------------ Call UserCreateController ------------");
        User user = new User(httpRequest.getParameter("userId").toString(),
                httpRequest.getParameter("password").toString(),
                httpRequest.getParameter("name").toString(),
                httpRequest.getParameter("email").toString());
        DataBase.addUser(user);
        httpResponse.sendRedirect("/index.html");
    }
}
