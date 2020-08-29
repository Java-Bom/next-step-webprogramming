package http;

import domain.model.User;
import http.dto.RequestInfo;
import util.HttpRequestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpHeaderContainer {
    private List<HttpRequestUtils.Pair> container = new ArrayList<>();
    private Map<String, String> cookies = new HashMap<>();

    public void extractHeader(BufferedReader br) throws IOException {
        String line = br.readLine();
        while (!"".equals(line)) {
            HttpRequestUtils.Pair pair = HttpRequestUtils.parseHeader(line);
            container.add(pair);
            line = br.readLine();
        }
        String cookiesString = getCookies();
        this.cookies = HttpRequestUtils.parseCookies(cookiesString);
    }

    private String getCookies() {
        return container.stream()
                .filter(pair -> pair.getKey().equals("Cookie"))
                .findFirst()
                .orElseGet(() -> new HttpRequestUtils.Pair("Cookie", ""))
                .getValue();
    }


    public User getLoginedUser() {
        HttpSession session = HttpSessionContainer.getSession(getSessionId());
        return (User) session.getAttribute("user");
    }

    public int getContentLength() {
        return Integer.parseInt(container.stream()
                .filter(pair -> pair.getKey().equals("Content-Length"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getValue());
    }

    public boolean logined(RequestInfo requestInfo) {
        return requestInfo.enableAccess(getLoginedUser());
    }

    public String getSessionId() {
        return cookies.get("JESSIONID");
    }
}
