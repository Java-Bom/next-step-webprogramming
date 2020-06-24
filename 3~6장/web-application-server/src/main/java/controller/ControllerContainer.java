package controller;

import controller.resource.CssController;
import controller.resource.DefaultController;
import controller.user.LoginController;
import controller.user.UserCreateController;
import controller.user.UserListController;

import java.util.Arrays;

public enum ControllerContainer {
    USER_CREATE("/user/create", new UserCreateController()),
    USER_LOGIN("/user/login", new LoginController()),
    USER_LIST("/user/list", new UserListController()),
    CSS(".css", new CssController());

    private final String url;
    private final Controller controller;

    ControllerContainer(final String url, final Controller controller) {
        this.url = url;
        this.controller = controller;
    }

    public static Controller findByUrl(final String requestUrl) {
        if (requestUrl.endsWith(".css")) {
            return CSS.controller;
        }
        return Arrays.stream(values())
                .filter(controller -> controller.url.equals(requestUrl))
                .map(ControllerContainer::getController)
                .findFirst()
                .orElse(new DefaultController());
    }

    public Controller getController() {
        return controller;
    }
}
