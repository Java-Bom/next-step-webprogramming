package http;

public enum HttpVersion {
    HTTP_1_1("HTTP/1.1");

    private final String mark;

    HttpVersion(final String mark) {
        this.mark = mark;
    }
}
