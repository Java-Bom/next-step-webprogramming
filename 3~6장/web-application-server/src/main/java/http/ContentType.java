package http;

import java.util.Arrays;

public enum ContentType {
    CSS(".css", "text/css"),
    JS(".js", "application/javascript"),
    HTML(".html", "text/html;charset=utf-8");

    private final String suffix;
    private final String value;

    ContentType(final String suffix, final String value) {
        this.suffix = suffix;
        this.value = value;
    }

    public static String findMarkByUrl(final String url) {
        return Arrays.stream(values())
                .filter(contentType -> url.endsWith(contentType.suffix))
                .findFirst()
                .orElse(HTML)
                .value;
    }

    public String getValue() {
        return value;
    }
}
