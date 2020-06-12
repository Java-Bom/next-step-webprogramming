package myServer.httpResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by jyami on 2020/06/12
 */
class HttpResponseEntityTest {

    private String testDirectory = "./src/test/resources/";

    @Test
    @DisplayName("response Forward 테스트")
    void responseForward() throws FileNotFoundException {
        HttpResponseEntity httpResponse =
                new HttpResponseEntity(createOutputStream("Http_Forward.txt"));
        httpResponse.forward("/index.html");
    }

    @Test
    @DisplayName("response redirect 테스트")
    void responseRedirect() throws FileNotFoundException {
        HttpResponseEntity httpResponse =
                new HttpResponseEntity(createOutputStream("Http_Redirect.txt"));
        httpResponse.sendRedirect("/index.html");
    }

    @Test
    @DisplayName("response cookie 테스트")
    void responseCookie() throws FileNotFoundException {HttpResponseEntity httpResponse =
            new HttpResponseEntity(createOutputStream("Http_Cookie.txt"));
        httpResponse.addHeader("Set-Cookie", "logined=true");
        httpResponse.forward("/index.html");
    }

    private OutputStream createOutputStream(String filename) throws FileNotFoundException {
        return new FileOutputStream(new File(testDirectory + filename));
    }
}