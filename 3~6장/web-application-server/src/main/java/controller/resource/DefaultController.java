package controller.resource;

import controller.AbstractController;
import http.request.HttpRequest;
import http.response.HttpResponse;

import java.io.IOException;

public class DefaultController extends AbstractController {

    @Override
    protected void doGet(final HttpRequest httpRequest, final HttpResponse httpResponse) throws IOException {
        httpResponse.forward("/index.html");
    }
}
