package webserver;

import http.request.HttpRequest;
import http.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.handler.ResponseHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//import webserver.container.RequestContainer;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream();
             OutputStream out = connection.getOutputStream()) {

            HttpRequest httpRequest = new HttpRequest(in);
            HttpResponse httpResponse = new HttpResponse(out);
            httpResponse.addSession(httpRequest.getCookie("JSESSION"));

            ResponseHandler responseHandler = HandlerMapping.find(httpRequest);

            HandlerAdapter handlerAdapter = new HandlerAdapter(responseHandler);
            handlerAdapter.doService(httpRequest, httpResponse);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
