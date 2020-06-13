package webserver;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Map;

import db.DataBase;
import model.User;
import myServer.RequestMapping;
import myServer.controller.Controller;
import myServer.httpRequest.HttpRequestEntity;
import myServer.httpResponse.HttpResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;
import util.IOUtils;
import util.UrlParser;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    @Override
    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(), connection.getPort());
        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            HttpRequestEntity requestEntity = new HttpRequestEntity(in);
            HttpResponseEntity responseEntity = new HttpResponseEntity(out);

            Controller controller = RequestMapping.getController(requestEntity.getStatusLine().getPath());
            if (controller == null) {
                String path = getDefaultPath(requestEntity.getStatusLine().getPath());
                responseEntity.forward(path);
            } else {
                controller.service(requestEntity, responseEntity);
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private String getDefaultPath(String path) {
        if (path.equals("/"))
            return "/index.html";
        return path;
    }
}
