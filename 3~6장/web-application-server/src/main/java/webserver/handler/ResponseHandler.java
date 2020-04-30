package webserver.handler;

import java.io.DataOutputStream;
import java.io.IOException;

public interface ResponseHandler {

    void response(DataOutputStream dos, String bodyString) throws IOException;
}
