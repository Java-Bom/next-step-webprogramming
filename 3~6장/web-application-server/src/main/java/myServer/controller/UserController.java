package myServer.controller;

import db.DataBase;
import model.User;
import myServer.exception.InternalServerException;
import myServer.exception.PathNotFoundException;
import myServer.httpRequest.HttpRequestEntity;
import myServer.httpRequest.RequestStatusLine;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by jyami on 2020/04/30
 */
public class UserController {

    public void createUser(HttpRequestEntity httpRequestEntity) {
        Map<String, String> bodies = httpRequestEntity.getBody();
        User user = new User(bodies.get("userId"), bodies.get("password"), bodies.get("name"), bodies.get("email"));
        DataBase.addUser(user);
    }

    enum Router {
        CREATE("POST", "/user/create", "createUser");

        private String method;
        private String path;
        private String processName;

        Router(String method, String path, String processName) {
            this.method = method;
            this.path = path;
            this.processName = processName;
        }

        public static Router findRoutingPath(RequestStatusLine statusLine) {
            return Arrays.stream(Router.values())
                    .filter(x -> x.method.equals(statusLine.getMethod()))
                    .filter(x -> x.path.equals(statusLine.getPath()))
                    .findFirst()
                    .orElseThrow(() -> new PathNotFoundException("잘못된 메서드 경로입니다."));
        }

        public Method executeProcess() {
            try {
                return UserController.class.getMethod(this.processName);
            } catch (NoSuchMethodException e) {
                throw new InternalServerException("메서드 이름과, 패쓰 설정을 다시 해주세요.", e.getCause());
            }
        }
    }
}