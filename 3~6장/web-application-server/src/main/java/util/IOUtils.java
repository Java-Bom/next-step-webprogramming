package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class IOUtils {
    /**
     * @param BufferedReader는
     *            Request Body를 시작하는 시점이어야
     * @param contentLength는
     *            Request Header의 Content-Length 값이다.
     * @return
     * @throws IOException
     */
    public static String readData(BufferedReader br, int contentLength) throws IOException {
        char[] body = new char[contentLength];
        br.read(body, 0, contentLength);
        return String.copyValueOf(body);
    }

    public static String readData(BufferedReader br, String contentLength) throws IOException {
        if (Objects.isNull(contentLength)) {
            return "";
        }
        char[] body = new char[Integer.parseInt(contentLength)];
        br.read(body, 0, Integer.parseInt(contentLength));
        return String.copyValueOf(body);
    }
}
