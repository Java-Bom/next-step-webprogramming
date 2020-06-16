package http;

public enum ResponseCode {
    SUCCESS(200, "OK");

    private final int httpStatusCode;
    private final String httpStatusMessage;


    ResponseCode(final int httpStatusCode, final String httpStatusMessage) {
        this.httpStatusCode = httpStatusCode;
        this.httpStatusMessage = httpStatusMessage;
    }
}
