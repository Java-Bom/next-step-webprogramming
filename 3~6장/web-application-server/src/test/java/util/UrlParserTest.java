package util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UrlParserTest {

    @Test
    void getMethod() {
        String requestLine = "GET /index.html HTTP/1.1";
        String method = UrlParser.getMethod(requestLine);
        assertThat(method).isEqualTo("GET");
    }

    @Test
    void getUri() {
        String requestLine = "GET /index.html HTTP/1.1";
        String uri = UrlParser.getUri(requestLine);
        assertThat(uri).isEqualTo("/index.html");
    }

    @Test
    void getHttpVersion() {
        String requestLine = "GET /index.html HTTP/1.1";
        String httpVersion = UrlParser.getHttpVersion(requestLine);
        assertThat(httpVersion).isEqualTo("HTTP/1.1");
    }

    @Test
    void getRequestPath() {
        String url = "/user/create?userId=jyami&password=q1w2e3&name=mj";
        String path = UrlParser.getRequestPath(url);
        assertThat(path).isEqualTo("/user/create");
    }

    @Test
    void getRequestParams() {
        String url = "/user/create?userId=jyami&password=q1w2e3&name=mj";
        String params = UrlParser.getRequestParams(url);
        assertThat(params).isEqualTo("userId=jyami&password=q1w2e3&name=mj");
    }
}