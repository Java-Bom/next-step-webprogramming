package controller;

import http.HttpMethod;
import http.request.HttpRequest;
import http.response.HttpResponse;

import java.io.IOException;

public abstract class AbstractController implements Controller {

    @Override
    public void service(final HttpRequest httpRequest, final HttpResponse httpResponse) throws IOException {
        HttpMethod httpMethod = httpRequest.getHttpRequestLine().getHttpMethod();
        if (httpMethod == HttpMethod.GET) {
            doGet(httpRequest, httpResponse);
        }
        if (httpMethod == HttpMethod.POST) {
            doPost(httpRequest, httpResponse);
        }
    }

    protected void doGet(final HttpRequest httpRequest, final HttpResponse httpResponse) throws IOException {
    }

    protected void doPost(final HttpRequest httpRequest, final HttpResponse httpResponse) throws IOException {
    }
}
