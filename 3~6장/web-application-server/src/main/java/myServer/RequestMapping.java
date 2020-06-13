package myServer;

import myServer.controller.Controller;
import myServer.controller.CreateUserController;
import myServer.controller.ListUserController;
import myServer.controller.LoginController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jyami on 2020/06/13
 */
public class RequestMapping {
    private static Map<String, Controller> controllers = new HashMap<>();
    static {
        controllers.put("/user/create", new CreateUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/list", new ListUserController());
    }

    public static Controller getController(String requestUrl){
        return controllers.get(requestUrl);
    }
}
