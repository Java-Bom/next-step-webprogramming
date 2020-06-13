package webserver.handler;

import http.request.HttpRequest;
import http.response.HttpResponse;

import java.io.IOException;
import java.util.function.Supplier;

public class UserFindResponseHandler implements ResponseHandler {
    private final Supplier<String> supplier;

    public UserFindResponseHandler(final Supplier<String> supplier) {
        this.supplier = supplier;
    }

    @Override
    public void response(final HttpRequest httpRequest, final HttpResponse httpResponse) throws IOException {
        String responseText = supplier.get();

        httpResponse.forwardWithBody(responseText);
    }

}
