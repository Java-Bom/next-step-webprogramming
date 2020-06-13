package http;

import util.HttpRequestUtils;
import util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HttpRequest {
    private final HttpMethod httpMethod;
    private final String path;
    private final HttpHeaders httpHeaders;
    private final HttpQueryParameters queryParameters;
    private final HttpBody httpBody;


    public HttpRequest(final InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        String line = br.readLine();
        this.httpMethod = HttpRequestUtils.extractHttpMethod(line);
        this.path = HttpRequestUtils.extractUrlPath(line);
        this.queryParameters = new HttpQueryParameters(this.path);
        this.httpHeaders = new HttpHeaders(toHttpHeaderStrings(br));
        this.httpBody = new HttpBody(IOUtils.readData(br, this.httpHeaders.getHeader(HttpHeader.CONTENT_LENGTH)));
    }

    private List<String> toHttpHeaderStrings(final BufferedReader br) throws IOException {
        List<String> headerStr = new ArrayList<>();
        String line = br.readLine();
        while (!"".equals(line)) {
            headerStr.add(line);
            line = br.readLine();
        }
        return headerStr;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Object getHeader(final HttpHeader httpHeader) {
        return this.httpHeaders.getHeader(httpHeader);
    }

    public String getQueryParameter(final String key) {
        return this.queryParameters.getQueryParam(key);
    }

    public Object getParameter(final String key) {
        return this.httpBody.getParameter(key);
    }

    public String getPath() {
        return path;
    }

    public HttpBody getHttpBody() {
        return httpBody;
    }
}
