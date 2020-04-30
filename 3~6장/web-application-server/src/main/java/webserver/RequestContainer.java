package webserver;

import util.IOUtils;
import webserver.handler.FileResponseHandler;
import webserver.handler.LoginResponseHandler;
import webserver.handler.ResponseHandler;
import webserver.handler.UserFindResponseHandler;
import webserver.handler.UserResponseHandler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestContainer {
    private RequestUriContainer uriContainer;
    private HttpHeaderContainer headerContainer;
    private static final Map<RequestInfo, ResponseHandler> RES;

    static {
        RES = new HashMap<>();
        initResource();
        RES.put(new RequestInfo(RequestUriContainer.Method.POST, "/user/create", true), new UserResponseHandler(new UserController()::create));
        RES.put(new RequestInfo(RequestUriContainer.Method.POST, "/user/login", true), new LoginResponseHandler(new UserController()::login));
        RES.put(new RequestInfo(RequestUriContainer.Method.GET, "/user/list", false), new UserFindResponseHandler(new UserController()::getUsers));

    }

    private String bodyString;

    private static void initResource() {
        ResourceExtractor resourceExtractor = new ResourceExtractor("./webapp");
        for (String path : resourceExtractor.getPaths()) {
            RES.put(new RequestInfo(RequestUriContainer.Method.GET, path, true), new FileResponseHandler(path));
        }
    }


    public void extractRequest(BufferedReader br) throws IOException {
        String uriString = br.readLine();
        this.uriContainer = new RequestUriContainer(uriString);
        this.headerContainer = new HttpHeaderContainer();
        headerContainer.extractHeader(br);
        if (this.uriContainer.isPost()) {
            this.bodyString = IOUtils.readData(br, headerContainer.getContentLength());
        }
    }


    public void response(DataOutputStream dos) throws IOException {
        RequestUriContainer.Method method = this.uriContainer.getMethod();
        String url = this.uriContainer.getUrl();
        RequestInfo requestInfo = new RequestInfo(method, url);

        ResponseHandler responseHandler = RES.entrySet().stream()
                .filter((entry) -> entry.getKey().equals(requestInfo))
                .filter(entry -> entry.getKey().enableAccess(this.headerContainer.getLogined()))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getValue();

        responseHandler.response(dos, this.bodyString);
    }



}
