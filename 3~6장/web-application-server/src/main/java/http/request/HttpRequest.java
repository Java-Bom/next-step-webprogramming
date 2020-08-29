package http.request;

import com.google.common.base.Strings;
import http.HttpCookie;
import http.HttpSession;
import http.HttpSessions;
import webserver.dto.RequestInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HttpRequest {

    private RequestUri requestUri;
    private RequestHeaders requestHeaders;
    private RequestParams requestParams;
    private HttpCookie httpCookie;
    private RequestBody requestBody;

    public HttpRequest(final InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        try {
            String requestUri = br.readLine();

            this.requestUri = new RequestUri(requestUri);
            this.requestHeaders = new RequestHeaders(collectHeaders(br));
            this.httpCookie = new HttpCookie(requestHeaders.getHeaders());
            if (this.requestUri.isPost()) {
                this.requestBody = new RequestBody(br, this.requestHeaders.getContentLength());
            }
            this.requestParams = new RequestParams(HttpMethod.valueOf(getMethod()), this.requestUri, this.requestBody);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private List<String> collectHeaders(final BufferedReader br) throws IOException {
        List<String> headerGroup = new ArrayList<>();
        String header = br.readLine();
        while (!Strings.isNullOrEmpty(header)) {
            headerGroup.add(header);
            header = br.readLine();
        }
        return headerGroup;
    }

    public RequestInfo getRequestInfo() {
        return new RequestInfo(HttpMethod.valueOf(getMethod()), requestUri.getPath());
    }

    public String getMethod() {
        return this.requestUri.getMethod();
    }

    public String getPath() {
        return this.requestUri.getPath();
    }

    public String getHeader(final String name) {
        return this.requestHeaders.get(name);
    }

    public String getParameter(final String key) {
        return this.requestParams.get(key);
    }

    public String getCookie(final String key) {
        return this.httpCookie.get(key);
    }

    public String getBodyString() {
        return this.requestBody.getBody();
    }

    public HttpSession getSession() {
        return HttpSessions.getSession(httpCookie.get("JESSIONID"));
    }
}
