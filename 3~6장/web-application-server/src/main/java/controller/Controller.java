package controller;

import http.CysHttpRequest;
import http.CysHttpResponse;

import java.io.IOException;

public interface Controller {

    void service(CysHttpRequest httpRequest, CysHttpResponse httpResponse) throws IOException;
}
