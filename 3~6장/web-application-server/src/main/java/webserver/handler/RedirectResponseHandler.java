package webserver.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.extractor.BodyExtractor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.function.Function;

public class RedirectResponseHandler<T> implements ResponseHandler {
    private static final Logger log = LoggerFactory.getLogger(IndexResponseHandler.class);
    private final Function<T, String> function;
    private final Class<T> requestType;

    public RedirectResponseHandler(final Function<T, String> function, final Class<T> requestType) {
        this.function = function;
        this.requestType = requestType;
    }

    @Override
    public void response(final DataOutputStream dos, final String bodyString) throws IOException {
        T extract = BodyExtractor.extract(requestType, bodyString);
        String response = this.function.apply(extract);

        try {
            dos.writeBytes("HTTP/1.1 302 Found\n \r\n");
            dos.writeBytes("Location: http://localhost:8080/" + response + "\r\n");
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
