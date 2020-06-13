package myServer.controller;

import db.DataBase;
import lombok.extern.slf4j.Slf4j;
import model.User;
import myServer.httpRequest.HttpRequestEntity;
import myServer.httpResponse.HttpResponseEntity;

import java.util.Map;

/**
 * Created by jyami on 2020/06/13
 */
@Slf4j
public class CreateUserController extends AbstractController {

    @Override
    protected void doPost(HttpRequestEntity request, HttpResponseEntity response) {
        Map<String, String> bodies = request.getBody();
        User user = new User(bodies.get("userId"), bodies.get("password"), bodies.get("name"), bodies.get("email"));
        log.debug("user : {}", user);
        DataBase.addUser(user);
        response.sendRedirect("/index.html");
    }

}
