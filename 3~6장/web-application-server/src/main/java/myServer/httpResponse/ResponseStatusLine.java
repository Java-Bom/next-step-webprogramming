package myServer.httpResponse;

import lombok.Builder;

/**
 * Created by jyami on 2020/05/30
 */
public class ResponseStatusLine {

    private String httpVersion;
    private HttpStatus httpStatus;

    private ResponseStatusLine(String httpVersion, HttpStatus httpStatus) {
        this.httpVersion = httpVersion;
        this.httpStatus = httpStatus;
    }

    public static ResponseStatusLine createResponseStatusLineWithHttpVersion(String httpVersion,HttpStatus httpStatus) {
        validateHttpVersion(httpVersion);
        return new ResponseStatusLine(httpVersion, httpStatus);
    }

    public static ResponseStatusLine createResponseStatusLine(HttpStatus httpStatus) {
        return new ResponseStatusLine("HTTP/1.1", httpStatus);
    }

    private static void validateHttpVersion(String httpVersion) {
        if (!httpVersion.equals("HTTP/1.0") && !httpVersion.equals("HTTP/1.1")) {
            throw new IllegalArgumentException("존재하지 않은 HTTP 버전입니다. - " + httpVersion);
        }
    }

    enum HttpStatus {
        OK(200, "OK"),
        FOUND(302, "Found");

        private int statusCode;
        private String statusMessage;

        HttpStatus(int statusCode, String statusMessage) {
            this.statusCode = statusCode;
            this.statusMessage = statusMessage;
        }
    }

    public String transferStatusFormat() {
        return String.format("%s %s %s", httpVersion, httpStatus.statusCode, httpStatus.statusMessage);
    }
}
