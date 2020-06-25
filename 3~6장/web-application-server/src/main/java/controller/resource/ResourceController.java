package controller.resource;

import controller.AbstractController;
import http.request.HttpRequest;
import http.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ResourceController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);

    @Override
    protected void doGet(final HttpRequest httpRequest, final HttpResponse httpResponse) throws IOException {
        log.info("------------ Call DefaultController ------------");
        httpResponse.forward(httpRequest.getHttpRequestLine().getPath());
    }
}
