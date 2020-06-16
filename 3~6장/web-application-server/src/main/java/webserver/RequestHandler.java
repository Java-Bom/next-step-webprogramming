package webserver;

import db.DataBase;
import http.HttpRequest;
import http.HttpResponse;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private final Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        boolean logined = false;
        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            HttpRequest httpRequest = new HttpRequest(in);
            HttpResponse httpResponse = new HttpResponse(out);

            if ("/user/create".equals(httpRequest.getHttpRequestLine().getPath())) {
                User user = new User(httpRequest.getParameter("userId").toString(), httpRequest.getParameter("password").toString(),
                        httpRequest.getParameter("name").toString(), httpRequest.getParameter("email").toString());
                DataBase.addUser(user);
                httpResponse.addHeader("Accept", "text/html,*/*;q=0.1");
                httpResponse.addHeader("Location", "/index.html");
                httpResponse.sendRedirect("/index.html");
            } else if ("/user/login".equals(httpRequest.getHttpRequestLine().getPath())) {
                User user = DataBase.findUserById(httpRequest.getHttpBody().getParameter("userId"));
                if (user.getPassword().equals(httpRequest.getHttpBody().getParameter("password"))) {
                    logined = true;
                    httpResponse.addHeader("Accept", "text/css,*/*;q=0.1");
                    httpResponse.addHeader("Location", "/index.html");
                    httpResponse.addHeader("Set-Cookie", "login=true");
                    httpResponse.sendRedirect("/index.html");
                } else {
                    httpResponse.forward("/usr/login_failed.html");
                }
            } else if ("/user/list".equals(httpRequest.getHttpRequestLine().getPath())) {
                if (!logined) {
                    httpResponse.forward("/usr/login.html");
                    return;
                }
                Collection<User> users = DataBase.findAll();
                StringBuilder stringBuilder = new StringBuilder();
                for (User user : users) {
                    stringBuilder.append(user);
                    stringBuilder.append("\n");
                }
                byte[] body = stringBuilder.toString().getBytes();
                httpResponse.forward(body);
            } else if (httpRequest.getHttpRequestLine().getPath().endsWith(".css")) {
                httpResponse.addHeader("Content-Type", "text/css");
                httpResponse.forward(httpRequest.getHttpRequestLine().getPath());
            } else {
                httpResponse.addHeader("Accept", "text/html,*/*;q=0.1");
                httpResponse.forward(httpRequest.getHttpRequestLine().getPath());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
