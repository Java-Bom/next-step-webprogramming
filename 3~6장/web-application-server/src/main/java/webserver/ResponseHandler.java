package webserver;

import java.io.DataOutputStream;
import java.io.IOException;

public interface ResponseHandler {
    void response(DataOutputStream dos) throws IOException;

    void response(DataOutputStream dos, String bodyString) throws IOException;
}
