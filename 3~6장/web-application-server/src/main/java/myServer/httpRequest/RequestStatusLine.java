package myServer.httpRequest;

import util.HttpRequestUtils;

import java.util.Collections;
import java.util.Map;

/**
 * Created by jyami on 2020/04/25
 */
public class RequestStatusLine {

    private static final String REGEX = " ";
    private static final String QUESTION_MARK = "?";

    private String method;
    private String url;
    private String path;
    private String httpVersion;
    private Map<String, String> params = Collections.EMPTY_MAP;

    public RequestStatusLine(String statusLine) {
        String[] statusGroup = statusLine.split(REGEX);
        this.method = statusGroup[0];
        this.url = statusGroup[1];
        this.path = this.url;
        this.httpVersion = statusGroup[2];
        if (url.contains(QUESTION_MARK)) {
            this.path = url.substring(0, url.indexOf(QUESTION_MARK));
            this.params = HttpRequestUtils.parseQueryString(url.substring(url.indexOf(QUESTION_MARK) + 1));
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
