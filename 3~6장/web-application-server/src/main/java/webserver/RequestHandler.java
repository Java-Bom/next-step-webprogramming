package webserver;

import converter.UserConverter;
import db.DataBase;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HttpRequestUtils;
import util.IOUtils;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Map;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            int lineOrder = 0;
            String line = br.readLine();
            boolean logined = false;
            while (!"".equals(line)) {
                if (line == null) {
                    return;
                }
                if (lineOrder == 0) {
                    DataOutputStream dos = new DataOutputStream(out);
                    String urlPath = HttpRequestUtils.extractUrlPath(line);
                    line = br.readLine();
                    lineOrder++;
                    if ("/user/create".equals(urlPath)) {
                        while (!"Content-Length".equals(HttpRequestUtils.parseHeader(line).getKey())) {
                            line = br.readLine();
                            lineOrder++;
                        }

                        int contentLength = Integer.parseInt(HttpRequestUtils.parseHeader(line).getValue());
                        log.info("contentLength: {}", contentLength);

                        while (!"".equals(line.trim())) {
                            line = br.readLine();
                            lineOrder++;
                        }
                        User user = UserConverter.convert(HttpRequestUtils.parseQueryString(IOUtils.readData(br, contentLength)));
                        log.info("ADD User = {}", user);
                        DataBase.addUser(user);
                        response302Header(dos, "/index.html");
                    }
                    if ("/user/login".equals(urlPath)) {
                        while (!"Content-Length".equals(HttpRequestUtils.parseHeader(line).getKey())) {
                            line = br.readLine();
                            lineOrder++;
                        }
                        int contentLength = Integer.parseInt(HttpRequestUtils.parseHeader(line).getValue());
                        log.info("contentLength: {}", contentLength);

                        while (!"".equals(line.trim())) {
                            line = br.readLine();
                            lineOrder++;
                        }

                        Map<String, String> loginParams = HttpRequestUtils.parseQueryString(IOUtils.readData(br, contentLength));
                        User user = DataBase.findUserById(loginParams.get("userId"));
                        if (user.getPassword().equals(loginParams.get("password"))) {
                            response302Header(dos, "/index.html", "logined=true");
                        }
                        response302Header(dos, "/user/login_failed.html", "logined=false");
                    }
                    if ("/user/list".equals(urlPath)) {
                        while (line != null && !line.contains("Cookie")) {
                            line = br.readLine();
                            lineOrder++;
                        }
                        System.out.println(line);
                        Map<String, String> cookies = HttpRequestUtils.parseCookies(HttpRequestUtils.parseHeader(line).getValue());
                        if (!Boolean.parseBoolean(cookies.get("logined"))) {
                            response302Header(dos, "/login.html");
                        }
                        Collection<User> users = DataBase.findAll();
                        StringBuilder stringBuilder = new StringBuilder();
                        for (User user : users) {
                            stringBuilder.append(user);
                        }
                        byte[] body = stringBuilder.toString().getBytes();
                        response200Header(new DataOutputStream(out), body.length);
                        responseBody(dos, body);
                        return;
                    }

                    byte[] body = Files.readAllBytes(new File("./webapp" + urlPath).toPath());
                    response200Header(dos, body.length);
                    responseBody(dos, body);
                    return;
                }
                lineOrder++;
            }

            DataOutputStream dos = new DataOutputStream(out);
            byte[] body = "Hello World".getBytes();
            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response302Header(DataOutputStream dos, String redirectUrl, String cookie) {
        try {
            /**
             * HTTP/1.1 302 Found
             * Location: http://www.iana.org/domains/example/
             */
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            dos.writeBytes("Set-Cookie:" + cookie + "\r\n");
            dos.writeBytes("Location: " + redirectUrl + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response302Header(DataOutputStream dos, String redirectUrl) {
        try {
            /**
             * HTTP/1.1 302 Found
             * Location: http://www.iana.org/domains/example/
             */
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            dos.writeBytes("Location: " + redirectUrl + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
