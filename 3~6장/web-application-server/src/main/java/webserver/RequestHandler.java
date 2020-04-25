package webserver;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Map;

import db.DataBase;
import model.User;
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
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());
        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            DataOutputStream dos = new DataOutputStream(out);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            int lineNumber = 0;

            String method = "";
            String url = "";
            String requestBody = "";
            int contentsLength = 0;
            String cookie ="";
            while (!"".equals(line)) {
                log.info(line);
                if (line == null) {
                    return;
                }
                if (lineNumber == 0) {
                    url = UrlParser.getUri(line);
                    method = UrlParser.getMethod(line);
                } else {
                    HttpRequestUtils.Pair pair = HttpRequestUtils.parseHeader(line);
                    if (pair.getKey().equals("Content-Length")) {
                        contentsLength = Integer.parseInt(pair.getValue());
                    }
                    if (pair.getKey().equals("Cookie")) {
                        cookie = pair.getValue();
                    }
                }
                line = br.readLine();
                lineNumber++;
            }
            if (method.equals("GET")) {
                String paramsStr = UrlParser.getRequestParams(requestBody);
                if (url.equals("/user/list")) {
                    Map<String, String> cookies = HttpRequestUtils.parseCookies(cookie);
                    if (Boolean.parseBoolean(cookies.get("logined"))) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (User user : DataBase.findAll()) {
                            stringBuilder.append(user);
                        }
                        byte[] body = stringBuilder.toString().getBytes();
                        response200Header(dos, body.length);
                        responseBody(dos, body);
                        return;
                    }
                }else if(url.endsWith(".css")){
                    byte[] body = Files.readAllBytes(new File("./webapp" + url).toPath());
                    response200CSSHeader(dos, body.length);
                    responseBody(dos, body);
                    return;
                }
            } else if (method.equals("POST")) {
                if (url.equals("/user/create")) {
                    requestBody = IOUtils.readData(br, contentsLength);
                    String paramsStr = UrlParser.getRequestParams(requestBody);
                    Map<String, String> params = HttpRequestUtils.parseQueryString(paramsStr);
                    User user = new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
                    DataBase.addUser(user);
                    response302Header(dos, "/index.html");
                    return;
                } else if (url.equals("/user/login")) {
                    requestBody = IOUtils.readData(br, contentsLength);
                    String paramsStr = UrlParser.getRequestParams(requestBody);
                    Map<String, String> params = HttpRequestUtils.parseQueryString(paramsStr);
                    User user = DataBase.findUserById(params.get("userId"));

                    if (user != null && user.getPassword().equals(params.get("password"))) {
                        url = "/index.html";
                        byte[] body = Files.readAllBytes(new File("./webapp" + url).toPath());
                        responseLogin200Header(dos, body.length, true);
                        responseBody(dos, body);
                    } else {
                        url = "/user/login_failed.html";
                        byte[] body = Files.readAllBytes(new File("./webapp" + url).toPath());
                        responseLogin200Header(dos, body.length, false);
                        responseBody(dos, body);
                    }
                    return;
                }

            }
            // response
            byte[] body = Files.readAllBytes(new File("./webapp" + url).toPath());
            response200Header(dos, body.length);
            responseBody(dos, body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void responseLogin200Header(DataOutputStream dos, int lengthOfBodyContent, boolean isSetCookie) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("Set-Cookie: " + isSetCookie + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response200CSSHeader(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/css;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
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

    private void response302Header(DataOutputStream dos, String location) {
        try {
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            dos.writeBytes("Location: " + location);
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
