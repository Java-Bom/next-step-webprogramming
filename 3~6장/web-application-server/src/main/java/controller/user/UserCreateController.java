package controller.user;

import controller.AbstractController;
import db.DataBase;
import http.request.HttpRequest;
import http.response.HttpResponse;
import model.User;

import java.io.IOException;

public class UserCreateController extends AbstractController {

    @Override
    public void doPost(final HttpRequest httpRequest, final HttpResponse httpResponse) throws IOException {
        User user = new User(httpRequest.getParameter("userId").toString(),
                httpRequest.getParameter("password").toString(),
                httpRequest.getParameter("name").toString(),
                httpRequest.getParameter("email").toString());
        DataBase.addUser(user);
        httpResponse.sendRedirect("/index.html");
    }
}
