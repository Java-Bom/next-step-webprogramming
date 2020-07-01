package myServer.httpResponse;


/**
 * Created by jyami on 2020/05/30
 */
public class ResponseStatusLine {

    private static final String HTTP_1_1 = "HTTP/1.1";
    private static final String HTTP_1_0 = "HTTP/1.0";

    private String httpVersion;
    private HttpStatus httpStatus;

    private ResponseStatusLine(String httpVersion, HttpStatus httpStatus) {
        this.httpVersion = httpVersion;
        this.httpStatus = httpStatus;
    }

    public static ResponseStatusLine createWithHttpVersion(String httpVersion, HttpStatus httpStatus) {
        validateHttpVersion(httpVersion);
        return new ResponseStatusLine(httpVersion, httpStatus);
    }

    public static ResponseStatusLine create(HttpStatus httpStatus) {
        return new ResponseStatusLine(HTTP_1_1, httpStatus);
    }

    private static void validateHttpVersion(String httpVersion) {
        if (!httpVersion.equals(HTTP_1_0) && !httpVersion.equals(HTTP_1_1)) {
            throw new IllegalArgumentException("존재하지 않은 HTTP 버전입니다. - " + httpVersion);
        }
    }
    public String transferStatusFormat() {
        return String.format("%s %s %s", httpVersion, httpStatus.getStatusCode(), httpStatus.getStatusMessage());
    }


}
