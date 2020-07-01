package myServer.httpRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyami on 2020/04/25
 */
class RequestStatusLineTest {

    @DisplayName("statusLine 값 매핑 확인")
    @Test
    void subStringTest() {
        String test = "GET /user/create HTTP/1.1";
        RequestStatusLine statusLine = new RequestStatusLine(test);
        assertThat(statusLine.getHttpVersion()).isEqualTo("HTTP/1.1");
        assertThat(statusLine.getMethod()).isEqualTo("GET");
        assertThat(statusLine.getPath()).isEqualTo("/user/create");
        assertThat(statusLine.getUrl()).isEqualTo("/user/create");
        assertThat(statusLine.getParams()).isEmpty();
    }

    @Test
    @DisplayName("statusLine 값 매핑 확인 with param")
    void subStringParamTest() {
        String test = "GET /user/create?userId=javajigi&password=password&name=Jaesung HTTP/1.1";
        RequestStatusLine statusLine = new RequestStatusLine(test);
        assertThat(statusLine.getHttpVersion()).isEqualTo("HTTP/1.1");
        assertThat(statusLine.getMethod()).isEqualTo("GET");
        assertThat(statusLine.getPath()).isEqualTo("/user/create");
        assertThat(statusLine.getUrl()).isEqualTo("/user/create?userId=javajigi&password=password&name=Jaesung");
        assertThat(statusLine.getParams()).isNotEmpty();
    }
}