package http.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

class HttpResponseTest {
    private static final String testDir = "./src/test/resources/";

    @DisplayName("Forward 응답")
    @Test
    void name() throws Exception {
        //given
        HttpResponse httpResponse = new HttpResponse(createOutputStream("Http_Forward.txt"));

        //then
        httpResponse.forward("/index.html");
    }

    @DisplayName("Location Redirect 응답")
    @Test
    void name1() throws Exception {
        //given
        HttpResponse httpResponse = new HttpResponse(createOutputStream("Http_Redirect.txt"));

        //then
        httpResponse.sendRedirect("/index.html");
    }

    @DisplayName("Cookies 응답")
    @Test
    void name2() throws Exception {
        //given
        HttpResponse httpResponse = new HttpResponse(createOutputStream("Http_Cookie.txt"));

        //then
        httpResponse.addHeader("Set-Cookie", "logined=true");
        httpResponse.forward("/index.html");
    }

    private OutputStream createOutputStream(String fileName) throws FileNotFoundException {
        return new FileOutputStream(new File(testDir + fileName));
    }
}