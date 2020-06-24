package controller.user;

import controller.AbstractController;
import db.DataBase;
import http.request.HttpRequest;
import http.response.HttpResponse;
import model.User;

import java.io.IOException;

public class LoginController extends AbstractController {

    @Override
    public void doPost(final HttpRequest httpRequest, final HttpResponse httpResponse) throws IOException {
        User user = DataBase.findUserById(httpRequest.getHttpBody().getParameter("userId"));
        if (user.getPassword().equals(httpRequest.getHttpBody().getParameter("password"))) {
            super.doPost(httpRequest, httpResponse);
            httpResponse.addHeader("Location", "/index.html");
            httpResponse.addHeader("Set-Cookie", "logined=true");
            httpResponse.sendRedirect("/index.html");
        } else {
            httpResponse.forward("/user/login_failed.html");
        }
    }
}
