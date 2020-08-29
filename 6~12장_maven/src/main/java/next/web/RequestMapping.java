package next.web;

import next.controller.CreateUserController;
import next.controller.HomeController;
import next.controller.ListUserController;
import next.controller.LogOutController;
import next.controller.LoginUserController;
import next.controller.ProfileController;
import next.controller.UpdateUserController;
import next.controller.UpdateUserFormController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private final Map<String, Controller> requestMappings = new HashMap<>();

    public RequestMapping() {
        this.requestMappings.put("/", new HomeController());
        this.requestMappings.put("/users", new ListUserController());
        this.requestMappings.put("/users/create", new CreateUserController());
        this.requestMappings.put("/users/form", new ForwardController("/user/form.jsp"));
        this.requestMappings.put("/users/profile", new ProfileController());
        this.requestMappings.put("/users/update-form", new UpdateUserFormController());
        this.requestMappings.put("/users/update", new UpdateUserController());
        this.requestMappings.put("/users/login-form", new ForwardController("/user/login.jsp"));
        this.requestMappings.put("/users/login", new LoginUserController());
        this.requestMappings.put("/users/logout", new LogOutController());
    }

    public Controller findController(String url) {
        return this.requestMappings.get(url);
    }

    void put(String url, Controller controller) {
        this.requestMappings.put(url, controller);
    }
}
