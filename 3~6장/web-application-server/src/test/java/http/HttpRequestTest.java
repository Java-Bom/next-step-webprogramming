package http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static http.HttpMethod.GET;
import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(request.getHttpMethod()).isEqualTo(GET);
        assertThat(request.getPath()).isEqualTo("/user/create?userId=javajigi&password=password2&name=aaa");
        assertThat(request.getHeader(HttpHeader.HOST)).isEqualTo(Optional.of("localhost"));
        assertThat(request.getHeader(HttpHeader.CONNECTION)).isEqualTo(Optional.of("keep-alive"));
    }

}