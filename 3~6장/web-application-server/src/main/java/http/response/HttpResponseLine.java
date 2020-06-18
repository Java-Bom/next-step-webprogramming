package http.response;

public enum HttpResponseLine {
    HTTP_SUCCESS("HTTP/1.1 200 OK \n"),
    HTTP_REDIRECT("HTTP/1.1 302 Found \n");

    private final String doc;

    HttpResponseLine(final String doc) {
        this.doc = doc;
    }

    public String getDoc() {
        return doc;
    }
}
