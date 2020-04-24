package webserver;

import java.io.DataOutputStream;
import java.io.IOException;

public interface ResponseHandler {
    void response(DataOutputStream dos) throws IOException;
}
