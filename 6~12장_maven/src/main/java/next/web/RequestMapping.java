package next.web;

import next.controller.HomeController;
import next.controller.qna.AddAnswerController;
import next.controller.qna.DeleteAnswerController;
import next.controller.qna.ShowController;
import next.controller.user.CreateUserController;
import next.controller.user.ListUserController;
import next.controller.user.LogOutController;
import next.controller.user.LoginUserController;
import next.controller.user.ProfileController;
import next.controller.user.UpdateUserController;
import next.controller.user.UpdateUserFormController;

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
        this.requestMappings.put("/qna/show", new ShowController());
        this.requestMappings.put("/qna/form", new ForwardController("/qna/form.jsp"));
        this.requestMappings.put("/api/qna/addAnswer", new AddAnswerController());
        this.requestMappings.put("/api/qna/deleteAnswer", new DeleteAnswerController());
    }

    public Controller findController(String url) {
        return this.requestMappings.get(url);
    }

    void put(String url, Controller controller) {
        this.requestMappings.put(url, controller);
    }
}
