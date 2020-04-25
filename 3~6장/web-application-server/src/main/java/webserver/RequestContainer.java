package webserver;

import util.IOUtils;

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
        RES.put(new RequestInfo(RequestUriContainer.Method.GET, "/index.html", true), new IndexResponseHandler("index.html"));
        RES.put(new RequestInfo(RequestUriContainer.Method.GET, "/user/form.html", true), new IndexResponseHandler("user/form.html"));
        RES.put(new RequestInfo(RequestUriContainer.Method.GET, "/user/login.html", true), new IndexResponseHandler("user/login.html"));
        RES.put(new RequestInfo(RequestUriContainer.Method.POST, "/user/create", true), new UserResponseHandler(new UserController()::create));
        RES.put(new RequestInfo(RequestUriContainer.Method.POST, "/user/login", true), new LoginResponseHandler(new UserController()::login));
        RES.put(new RequestInfo(RequestUriContainer.Method.GET, "/user/list", false), new UserFindResponseHandler(new UserController()::getUsers));
    }

    private String bodyString;

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
        String logined = headerContainer.getLogined();
        RequestInfo requestInfo = new RequestInfo(method, url, logined);

        if (requestInfo.isGet()) {
            RES.get(requestInfo)
                    .response(dos);

        } else if (requestInfo.isPost()) {
            RES.get(requestInfo)
                    .response(dos, bodyString);
        }
    }
}
