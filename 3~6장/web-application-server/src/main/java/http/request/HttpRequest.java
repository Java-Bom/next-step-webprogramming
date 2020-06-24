package http.request;

import http.HttpBody;
import http.HttpHeader;
import http.HttpHeaders;
import util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HttpRequest {
    private static final String END_MARK = "";

    private final HttpRequestLine httpRequestLine;
    private final HttpHeaders httpHeaders;
    private final HttpQueryParameters queryParameters;
    private final HttpBody httpBody;


    public HttpRequest(final InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        String line = br.readLine();
        if (line == null) {
            throw new IllegalArgumentException("null 요청은 HttpRequest가 될 수 없습니다");
        }
        this.httpRequestLine = new HttpRequestLine(line);
        this.queryParameters = new HttpQueryParameters(this.httpRequestLine.getPath());
        this.httpHeaders = new HttpHeaders(toHttpHeaderStrings(br));
        this.httpBody = new HttpBody(IOUtils.readData(br, this.httpHeaders.getHeader(HttpHeader.CONTENT_LENGTH)));
    }

    private List<String> toHttpHeaderStrings(final BufferedReader br) throws IOException {
        List<String> headerStr = new ArrayList<>();
        String line = br.readLine();

        while (!END_MARK.equals(line)) {
            headerStr.add(line);
            line = br.readLine();
        }
        return headerStr;
    }

    public HttpRequestLine getHttpRequestLine() {
        return httpRequestLine;
    }

    public Object getHeader(final HttpHeader httpHeader) {
        return this.httpHeaders.getHeader(httpHeader);
    }

    public Object getCookie(final String key) {
        return this.httpHeaders.getCookie(key);
    }

    public String getQueryParameter(final String key) {
        return this.queryParameters.getQueryParam(key);
    }

    public Object getParameter(final String key) {
        return this.httpBody.getParameter(key);
    }

    public HttpBody getHttpBody() {
        return httpBody;
    }
}
