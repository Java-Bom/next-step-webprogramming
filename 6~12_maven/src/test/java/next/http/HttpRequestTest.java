package next.http;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jyami on 2020/08/27
 */
public class HttpRequestTest {
    @Test
    public void name() {
        HttpRequest hello = new HttpRequest("hello", HttpMethod.POST);
        HttpRequest hello1 = new HttpRequest("hello", HttpMethod.POST);
        assertTrue(hello.equals(hello1));
    }
}