package controller;

import controller.resource.ResourceController;
import controller.user.LoginController;
import controller.user.UserCreateController;
import controller.user.UserListController;

import java.util.Arrays;

public enum ControllerContainer {
    USER_CREATE("/user/create", new UserCreateController()),
    USER_LOGIN("/user/login", new LoginController()),
    USER_LIST("/user/list", new UserListController());

    private final String url;
    private final Controller controller;

    ControllerContainer(final String url, final Controller controller) {
        this.url = url;
        this.controller = controller;
    }

    public static Controller findByUrl(final String requestUrl) {
        return Arrays.stream(values())
                .filter(controller -> controller.url.equals(requestUrl))
                .map(ControllerContainer::getController)
                .findFirst()
                .orElse(new ResourceController());
    }

    public Controller getController() {
        return controller;
    }
}
