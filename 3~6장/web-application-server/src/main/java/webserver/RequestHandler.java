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
import java.nio.charset.StandardCharsets;
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
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String line = br.readLine();
            log.debug("request line: {} ", line);

            if (line == null) {
                return;
            }
            String[] tokens = line.split(" ");
            int contentLength = 0;
            boolean logined = false;
            while (!"".equals(line)) {
                log.debug("header: {}", line);
                line = br.readLine();
                if (line.contains("Content-Length")) {
                    contentLength = getContentLength(line);
                }
                if (line.contains("Cookie")) {
                    logined = isLogin(line);
                }
            }
            String url = tokens[1];
            if ("/user/create".equals(url)) {
                String body = IOUtils.readData(br, contentLength);
                Map<String, String> params = HttpRequestUtils.parseQueryString(body);
                User user = UserConverter.convert(params);
                log.debug("ADD User = {}", user);
                DataBase.addUser(user);
                DataOutputStream dos = new DataOutputStream(out);
                response302Header(dos, "/index.html");
            } else if ("/user/login".equals(url)) {
                String body = IOUtils.readData(br, contentLength);
                Map<String, String> loginParams = HttpRequestUtils.parseQueryString(body);
                User user = DataBase.findUserById(loginParams.get("userId"));
                if (user.getPassword().equals(loginParams.get("password"))) {
                    response302Header(new DataOutputStream(out), "/index.html", "logined=true");
                } else {
                    responseResource(out, "/usr/login_failed.html");
                }
            } else if ("/user/list".equals(url)) {
                if (!logined) {
                    responseResource(out, "/usr/login.html");
                    return;
                }
                Collection<User> users = DataBase.findAll();
                StringBuilder stringBuilder = new StringBuilder();
                for (User user : users) {
                    stringBuilder.append(user);
                    stringBuilder.append("\n");
                }
                byte[] body = stringBuilder.toString().getBytes();
                DataOutputStream dos = new DataOutputStream(out);
                response200Header(dos, body.length);
                responseBody(dos, body);
            } else if (url.endsWith(".css")) {
                DataOutputStream dos = new DataOutputStream(out);
                byte[] body = Files.readAllBytes(new File("./webapp" + url).toPath());
                response200CssHeader(dos, body.length);
                responseBody(dos, body);
            } else {
                responseResource(out, url);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private boolean isLogin(final String line) {
        String[] headerTokens = line.split(":");
        Map<String, String> cookies = HttpRequestUtils.parseCookies(headerTokens[1].trim());
        String value = cookies.get("logined");
        if (value == null) {
            return false;
        }
        return true;
    }

    private void responseResource(final OutputStream out, final String url) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        byte[] body = Files.readAllBytes(new File("./webapp" + url).toPath());
        response200Header(dos, body.length);
        responseBody(dos, body);
    }

    private int getContentLength(final String line) {
        String[] headerTokens = line.split(":");
        return Integer.parseInt(headerTokens[1].trim());
    }

    private void response200CssHeader(DataOutputStream dos, int length) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/css\r\n");
            dos.writeBytes("Content-Length: " + length + "\r\n");
            dos.writeBytes("\r\n");
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
            dos.writeBytes("Accept: text/css,*/*;q=0.1 \r\n");
            dos.writeBytes("Location: " + redirectUrl + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html\r\n");
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
