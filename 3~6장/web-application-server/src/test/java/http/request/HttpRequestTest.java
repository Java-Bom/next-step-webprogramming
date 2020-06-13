package http.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class HttpRequestTest {

    private static final String testDir = "./src/test/resources/";

    @DisplayName("GET 요청 처리하기")
    @Test
    void request() throws Exception {
        //given
        InputStream in = new FileInputStream(new File(testDir + "Http_GET.txt"));

        //when
        HttpRequest httpRequest = new HttpRequest(in);

        //then
        assertThat(httpRequest.getMethod()).isEqualTo("GET");
        assertThat(httpRequest.getPath()).isEqualTo("/user/create");
        assertThat(httpRequest.getHeader("Connection")).isEqualTo("keep-alive");
        assertThat(httpRequest.getParameter("userId")).isEqualTo("pci2676");
    }

    @DisplayName("POST 요청 처리하기")
    @Test
    void request2() throws Exception {
        //given
        InputStream in = new FileInputStream(new File(testDir + "Http_POST.txt"));

        //when
        HttpRequest httpRequest = new HttpRequest(in);

        //then
        assertThat(httpRequest.getMethod()).isEqualTo("POST");
        assertThat(httpRequest.getPath()).isEqualTo("/user/create");
        assertThat(httpRequest.getHeader("Connection")).isEqualTo("keep-alive");
        assertThat(httpRequest.getHeader("Content-Length")).isEqualTo("44");
        assertThat(httpRequest.getHeader("Content-Type")).isEqualTo("application/x-www-form-urlencoded");
        assertThat(httpRequest.getHeader("Accept")).isEqualTo("*/*");
        assertThat(httpRequest.getParameter("userId")).isEqualTo("pci2676");
    }
}