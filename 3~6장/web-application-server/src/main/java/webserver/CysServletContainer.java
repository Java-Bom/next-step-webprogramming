package webserver;

import controller.*;
import http.CysHttpRequest;
import http.CysHttpResponse;
import http.RequestUriContainer;
import http.dto.RequestInfo;
import util.extractor.ResourceExtractor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//테스트
public class CysServletContainer {
    private static final Map<RequestInfo, Controller> RES = new HashMap<>();

    static {
        initResource();
        RES.put(new RequestInfo(RequestUriContainer.Method.POST, "/user/create", true), new UserCreateController());
        RES.put(new RequestInfo(RequestUriContainer.Method.POST, "/user/login", true), new LoginController());
        RES.put(new RequestInfo(RequestUriContainer.Method.GET, "/user/list", false), new UserFindController());
        RES.put(new RequestInfo(RequestUriContainer.Method.GET, "/", true),new FileController("index.html"));
    }

    private static void initResource() {
        ResourceExtractor resourceExtractor = new ResourceExtractor("./webapp");
        for (String path : resourceExtractor.getPaths()) {
            RES.put(new RequestInfo(RequestUriContainer.Method.GET, path, true), new FileController(path));
        }
    }

    public void run(CysHttpRequest cysHttpRequest, CysHttpResponse cysHttpResponse) throws IOException {
        Controller controller = findHandler(cysHttpRequest);
        controller.service(cysHttpRequest, cysHttpResponse);
    }

    private Controller findHandler(CysHttpRequest cysHttpRequest) {
        RequestInfo requestInfo = cysHttpRequest.getInfo();

        return RES.entrySet().stream()
                .filter((entry) -> entry.getKey().equals(requestInfo))
                .filter(entry -> cysHttpRequest.enableAccessWithCookie(entry.getKey()))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getValue();
    }
}
