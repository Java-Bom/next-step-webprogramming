package myServer.controller;

import db.DataBase;
import model.User;
import myServer.httpRequest.HttpRequestEntity;
import myServer.httpResponse.HttpResponseEntity;
import util.HttpRequestUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by jyami on 2020/06/13
 */
public class ListUserController extends AbstractController {

    @Override
    protected void doGet(HttpRequestEntity request, HttpResponseEntity response) {
        if (!isLogin(request.getCookie())) {
            response.sendRedirect("/user/login.html");
            return;
        }
        Collection<User> userCollection = DataBase.findAll();
        StringBuilder sb = new StringBuilder();
        for (User user : userCollection)
            makeUserAsHtmlFormat(sb, user);
        response.forwardBody(sb.toString());
    }

    private void makeUserAsHtmlFormat(StringBuilder sb, User user) {
        sb.append("<tr>");
        sb.append("<td>").append(user.getUserId()).append("</td>");
        sb.append("<td>").append(user.getName()).append("</td>");
        sb.append("<td>").append(user.getEmail()).append("</td>");
        sb.append("</tr>");
    }

    private boolean isLogin(String cookie) {
        Map<String, String> cookies = HttpRequestUtils.parseCookies(cookie);
        return Boolean.parseBoolean(cookies.get("logined"));
    }
}
