package myServer.httpRequest;

import myServer.exception.InternalServerException;
import util.HttpRequestUtils;
import util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jyami on 2020/04/25
 */
public class HttpRequestEntity {
    private static final String STATUS_LINE = "STATUS_LINE";
    private RequestStatusLine statusLine;
    private Map<String, String> headers;
    private Map<String, String> body;

    public HttpRequestEntity(BufferedReader br) {
        try {
            this.headers = makeHttpRequestHeaderMap(br);
            this.statusLine = new RequestStatusLine(headers.get(STATUS_LINE));
            if(getContentLength() != null){
                this.body = makeHttpRequestBodyMap(br);
            }
        } catch (IOException e) {
            throw new InternalServerException(e.getMessage(), e.getCause());
        }
    }

    public RequestStatusLine getStatusLine() {
        return statusLine;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public String getContentLength() {
        return headers.get("Content-Length");
    }

    public String getCookie() {
        return headers.get("Cookie");
    }

    public String getHost() {
        return headers.get("Host");
    }

    public String getConnection() {
        return headers.get("Connection");
    }

    private Map<String, String> makeHttpRequestHeaderMap(BufferedReader br) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String line = br.readLine();
        headers.put(STATUS_LINE, line);
        while (!"".equals(line = br.readLine())) {
            if (line == null) {
                break;
            }
            HttpRequestUtils.Pair pair = HttpRequestUtils.parseHeader(line);
            headers.put(pair.getKey(), pair.getValue());
        }
        return headers;
    }

    private Map<String, String> makeHttpRequestBodyMap(BufferedReader br) throws IOException{
        String body = IOUtils.readData(br, Integer.parseInt(getContentLength()));
        return HttpRequestUtils.parseQueryString(body);
    }
}
