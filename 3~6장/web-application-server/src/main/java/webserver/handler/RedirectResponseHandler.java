package webserver.handler;

import http.request.HttpRequest;
import http.response.HttpResponse;
import webserver.extractor.BodyExtractor;

import java.util.function.Function;

public class RedirectResponseHandler<T> implements ResponseHandler {
    private final Function<T, String> function;
    private final Class<T> requestType;

    public RedirectResponseHandler(final Function<T, String> function, final Class<T> requestType) {
        this.function = function;
        this.requestType = requestType;
    }

    @Override
    public void response(final HttpRequest httpRequest, final HttpResponse httpResponse) {
        T extract = BodyExtractor.extract(requestType, httpRequest.getBodyString());
        String response = this.function.apply(extract);

        httpResponse.sendRedirect("/" + response);
    }
}
