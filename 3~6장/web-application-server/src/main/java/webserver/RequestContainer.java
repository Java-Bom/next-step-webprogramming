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
    private static final Map<String, ResponseHandler> RES;

    static {
        RES = new HashMap<>();
        RES.put("GET/index.html", new IndexResponseHandler("index.html"));
        RES.put("GET/user/form.html", new IndexResponseHandler("user/form.html"));
        RES.put("POST/user/create", new UserResponseHandler());
    }


    public void extractRequest(BufferedReader br) throws IOException {
        String uriString = br.readLine();
        this.uriContainer = new RequestUriContainer(uriString);
        this.headerContainer = new HttpHeaderContainer();
        headerContainer.extractHeader(br);

        if (uriContainer.isPost()) {
            String bodyString = IOUtils.readData(br, headerContainer.getContentLength());
            Class<?> bodyClass = BodyContainer.findBy(uriContainer.getUrl());
            BodyContainer.BodyContainer(bodyClass, bodyString);
        }
    }


    public void response(DataOutputStream dos) throws IOException {
        RES.get(this.uriContainer.getUri())
                .response(dos);
    }
}
