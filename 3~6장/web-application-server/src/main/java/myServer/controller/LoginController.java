package myServer.controller;

import db.DataBase;
import lombok.extern.slf4j.Slf4j;
import model.User;
import myServer.httpRequest.HttpRequestEntity;
import myServer.httpRequest.RequestStatusLine;
import myServer.httpResponse.HttpResponseEntity;

/**
 * Created by jyami on 2020/06/13
 */
@Slf4j
public class LoginController extends AbstractController {

    @Override
    protected void doPost(HttpRequestEntity request, HttpResponseEntity response) {
        User user = DataBase.findUserById(request.getBody().get("userId"));
        if (user != null){
            if(user.login(request.getBody().get("password"))){
                response.addHeader("Set-Cookie", "logined=true");
                response.sendRedirect("/index.html");
            }else{
                response.sendRedirect("/user/login_failed.html");
            }
        }else{
            response.sendRedirect("/user/login_failed.html");
        }
    }
}
