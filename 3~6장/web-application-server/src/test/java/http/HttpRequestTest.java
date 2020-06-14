package http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static http.HttpMethod.GET;
import static http.HttpMethod.POST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HttpRequestTest {
    private String testDirectory = "./src/test/resources/";

    /**
     * GET /user/create?userId=javajigi&password=password2&name=aaa HTTP/1.1
     * Host: localhost
     * Accept:
     * Connection:keep-alive
     *
     * @throws IOException
     */
    @DisplayName("GET HttpRequest파싱")
    @Test
    void request_GET() throws IOException {
        InputStream in = new FileInputStream(new File(testDirectory + "Http_GET.txt"));
        HttpRequest request = new HttpRequest(in);

        assertAll(
                () -> assertThat(request.getHttpRequestLine().getHttpMethod()).isEqualTo(GET),
                () -> assertThat(request.getHttpRequestLine().getPath()).isEqualTo("/user/create?userId=javajigi&password=password2&name=aaa"),
                () -> assertThat(request.getHeader(HttpHeader.HOST)).isEqualTo("localhost"),
                () -> assertThat(request.getHeader(HttpHeader.CONNECTION)).isEqualTo("keep-alive")
        );
    }

    /**
     * POST /user/create HTTP/1.1
     * Host: localhost:8080
     * Connection: keep-alive
     * Content-Length: 46
     * Content-Type: application/x-www-form-urlencoded
     * <p>
     * userId=javajigi&password=password2&name=aaa
     *
     * @throws IOException
     */
    @DisplayName("POST HttpRequest파싱")
    @Test
    void request_POST() throws IOException {
        InputStream in = new FileInputStream(new File(testDirectory + "Http_POST.txt"));
        HttpRequest request = new HttpRequest(in);

        assertAll(
                () -> assertThat(request.getHttpRequestLine().getHttpMethod()).isEqualTo(POST),
                () -> assertThat(request.getHttpRequestLine().getPath()).isEqualTo("/user/create"),
                () -> assertThat(request.getHeader(HttpHeader.HOST)).isEqualTo("localhost"),
                () -> assertThat(request.getHeader(HttpHeader.CONNECTION)).isEqualTo("keep-alive"),
                () -> assertThat(request.getHttpBody().getParameter("userId")).isEqualTo("javajigi")
        );
    }

}