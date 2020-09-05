package core.mvc;

import next.controller.HomeController;
import next.controller.auth.UserLoginController;
import next.controller.auth.UserLogoutController;
import next.controller.qna.ShowController;
import next.controller.user.*;
import next.http.HttpMethod;
import next.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jyami on 2020/08/27
 */
public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private Map<HttpRequest, Controller> mappings = new HashMap<>();

    void initMapping() {
        mappings.put(new HttpRequest("/", HttpMethod.GET), new HomeController());
        mappings.put(new HttpRequest("/user/list", HttpMethod.GET), new ListUserController());
        mappings.put(new HttpRequest("/user/create", HttpMethod.GET), new ForwardController("/user/form.jsp"));
        mappings.put(new HttpRequest("/user/create", HttpMethod.POST), new CreateUserController());
        mappings.put(new HttpRequest("/user/login", HttpMethod.GET), new ForwardController("/user/login.jsp"));
        mappings.put(new HttpRequest("/user/login", HttpMethod.POST), new UserLoginController());
        mappings.put(new HttpRequest("/user/logout", HttpMethod.GET), new UserLogoutController());
        mappings.put(new HttpRequest("/user/update", HttpMethod.GET), new UpdateUserFormController());
        mappings.put(new HttpRequest("/user/update", HttpMethod.POST), new UpdateUserController());
        mappings.put(new HttpRequest("/question/show", HttpMethod.GET), new ShowController());

        logger.info("initialized request mappings!");
    }

    public Controller findController(String url, String method) {
        return mappings.get(new HttpRequest(url, HttpMethod.valueOf(method)));
    }

    void put(String url, HttpMethod method, Controller controller) {
        mappings.put(new HttpRequest(url, method), controller);
    }
}
