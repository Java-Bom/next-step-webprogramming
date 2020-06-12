package myServer.httpRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/04/30
 */
class HttpRequestEntityTest {

    private String testDirectory = "./src/test/resources/";

    @Test
    @DisplayName("Get 요청을 보낸다.")
    void requestGet() throws FileNotFoundException {
        InputStream in = new FileInputStream(new File(testDirectory + "Http_GET.txt"));
        HttpRequestEntity requestEntity = new HttpRequestEntity(in);

        RequestStatusLine statusLine = requestEntity.getStatusLine();

        assertThat(statusLine.getMethod()).isEqualTo("GET");
        assertThat(statusLine.getPath()).isEqualTo("/user/create");
        assertThat(requestEntity.getConnection()).isEqualTo("keep-alive");
        assertThat(statusLine.getParams().get("userId")).isEqualTo("javabom");
    }

    @Test
    @DisplayName("Post 요청을 보낸다.")
    void requestPost() throws FileNotFoundException {
        InputStream in = new FileInputStream(new File(testDirectory + "Http_POST.txt"));
        HttpRequestEntity requestEntity = new HttpRequestEntity(in);

        RequestStatusLine statusLine = requestEntity.getStatusLine();

        assertThat(statusLine.getMethod()).isEqualTo("POST");
        assertThat(statusLine.getPath()).isEqualTo("/user/create");
        assertThat(requestEntity.getConnection()).isEqualTo("keep-alive");
        assertThat(requestEntity.getBody().get("userId")).isEqualTo("javabom");
    }

    @Test
    @DisplayName("header 파싱")
    void name() {
        String header = "GET http://goddaehee.tistory.com/168 HTTP/1.1\n" +
                "Host: goddaehee.tistory.com\n" +
                "Connection: keep-alive\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\n" +
                "Referer: http://goddaehee.tistory.com/\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7;";
        InputStream inputStream = new ByteArrayInputStream(header.getBytes());
        HttpRequestEntity httpRequestEntity = new HttpRequestEntity(inputStream);

        assertAll(
                () -> assertThat(httpRequestEntity.getStatusLine().getMethod()).isEqualTo("GET"),
                () -> assertThat(httpRequestEntity.getHost()).isEqualTo("goddaehee.tistory.com"),
                () -> assertThat(httpRequestEntity.getCookie()).isNull()
        );
    }

    @DisplayName("httpRequest Entity header body")
    @Test
    void httpRequestWithHeaderBody() {
        String bodyConents = "userId=javajigi&password=password&name=Jaesung";
        String header = "POST /user/create HTTP/1.1\n" +
                "Host: localhost:8080\n" +
                "Connection: keep-alive\n" +
                "Content-Length: "+ bodyConents.length()+"\n" +
                "Content-Type: application/x-www-form-urlencoded\n" +
                "Accept: */*\n" +
                "\n" +
                bodyConents;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(header.getBytes());
        HttpRequestEntity httpRequestEntity = new HttpRequestEntity(byteArrayInputStream);

        assertThat(httpRequestEntity.getBody().get("userId")).isEqualTo("javajigi");
        assertThat(httpRequestEntity.getBody().get("password")).isEqualTo("password");
        assertThat(httpRequestEntity.getBody().get("name")).isEqualTo("Jaesung");
    }
}