package myServer.httpResponse;

/**
 * Created by jyami on 2020/06/13
 */
public enum HttpStatus {
    OK(200, "OK"),
    FOUND(302, "Found");

    private int statusCode;
    private String statusMessage;

    HttpStatus(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
