package webserver;

import controller.UserController;
import domain.model.User;
import http.request.HttpMethod;
import http.request.HttpRequest;
import webserver.dto.RequestInfo;
import webserver.extractor.ResourceExtractor;
import webserver.handler.FileResponseHandler;
import webserver.handler.IndexResponseHandler;
import webserver.handler.LoginResponseHandler;
import webserver.handler.RedirectResponseHandler;
import webserver.handler.ResponseHandler;
import webserver.handler.UserFindResponseHandler;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {

    private static final Map<RequestInfo, ResponseHandler> RES = new HashMap<>();

    static {
        initResource();
        RES.put(new RequestInfo(HttpMethod.GET, "/", true), new IndexResponseHandler("/index.html"));
        RES.put(new RequestInfo(HttpMethod.POST, "/user/create", true), new RedirectResponseHandler<>(new UserController()::create, User.class));
        RES.put(new RequestInfo(HttpMethod.POST, "/user/login", true), new LoginResponseHandler(new UserController()::login));
        RES.put(new RequestInfo(HttpMethod.GET, "/user/list", false), new UserFindResponseHandler(new UserController()::getUsers));
    }

    private static void initResource() {
        ResourceExtractor resourceExtractor = new ResourceExtractor("./webapp");
        for (String path : resourceExtractor.getPaths()) {
            RES.put(new RequestInfo(HttpMethod.GET, path, true), new FileResponseHandler(path));
        }
    }

    public static ResponseHandler find(HttpRequest httpRequest) {
        RequestInfo requestInfo = httpRequest.getRequestInfo();
        String logined = httpRequest.getCookie("logined");

        return RES.entrySet().stream()
                .filter((entry) -> entry.getKey().equals(requestInfo))
                .filter(entry -> entry.getKey().enableAccess(logined))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getValue();
    }
}
