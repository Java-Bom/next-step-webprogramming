package http;

import http.dto.RequestInfo;
import util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CysHttpRequest {

    private RequestUriContainer uriContainer;
    private HttpHeaderContainer headerContainer;
    private String bodyString;

    public CysHttpRequest(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        extractRequest(br);
    }

    private void extractRequest(BufferedReader br) throws IOException {
        String uriString = br.readLine();
        this.uriContainer = new RequestUriContainer(uriString);
        this.headerContainer = new HttpHeaderContainer();
        headerContainer.extractHeader(br);
        if (this.uriContainer.isPost()) {
            this.bodyString = IOUtils.readData(br, headerContainer.getContentLength());
        }
    }

    public RequestInfo getInfo() {
        RequestUriContainer.Method method = this.uriContainer.getMethod();
        String url = this.uriContainer.getUrl();
        return new RequestInfo(method, url);
    }

    public boolean enableAccess(RequestInfo requestInfo) {
        return this.headerContainer.logined(requestInfo);
    }

    public boolean enableAccessWithCookie(RequestInfo requestInfo) {
        return this.headerContainer.logined(requestInfo);
    }

    public String getBodyString() {
        return this.bodyString;
    }

    public boolean hasSessionId() {
        return headerContainer.getSessionId() != null;
    }

    public HttpSession getSession() {
        return HttpSessionContainer.getSession(headerContainer.getSessionId());
    }
}
