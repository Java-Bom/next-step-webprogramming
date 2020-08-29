package controller;

import db.DataBase;
import domain.model.User;
import http.CysHttpRequest;
import http.CysHttpResponse;

import java.io.IOException;
import java.util.List;

public class UserFindController implements Controller {

    @Override
    public void service(CysHttpRequest httpRequest, CysHttpResponse httpResponse) throws IOException {
        if (httpRequest.getSession().getAttribute("user") == null) {
            httpResponse.responseRedirect("index.html");
            return;
        }
        String responseText = getUsers();

        byte[] body = responseText.getBytes();
        httpResponse.response200Header(body);
    }

    public String getUsers() {
        List<User> users = DataBase.findAll();
        StringBuilder builder = new StringBuilder();
        for (User user : users) {
            builder.append(user.toString() + "\n");
        }
        return builder.toString();
    }
}
