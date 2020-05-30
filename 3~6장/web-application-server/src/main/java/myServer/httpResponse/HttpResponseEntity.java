package myServer.httpResponse;

import java.io.DataOutputStream;
import java.util.Map;

/**
 * Created by jyami on 2020/04/30
 */
public class HttpResponseEntity {

    private ResponseStatusLine statusLine;
    private Map<String, String> headers;
    private Map<String, String> body;

    public HttpResponseEntity() {

    }

    public void write(DataOutputStream dos){

    }

    private byte[] response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        StringBuilder builder = new StringBuilder();

        builder.append("HTTP/1.1 200 OK \r\n");
        builder.append("Content-Type: text/html;charset=utf-8\r\n");
        builder.append("Content-Length: " + lengthOfBodyContent + "\r\n");
        builder.append("\r\n");

        return builder.toString().getBytes();
    }
}
