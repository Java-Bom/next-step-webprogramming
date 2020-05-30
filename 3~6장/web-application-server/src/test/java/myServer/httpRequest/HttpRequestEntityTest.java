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

    @DisplayName("header 파싱")
    @Test
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
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        HttpRequestEntity httpRequestEntity = new HttpRequestEntity(bufferedReader);

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

        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(header.getBytes())));
        HttpRequestEntity httpRequestEntity = new HttpRequestEntity(br);

        assertThat(httpRequestEntity.getBody()).isEqualTo("userId=javajigi&password=password&name=Jaesung");
    }
}