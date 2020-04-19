package util;

public class UrlParser {
    public static String getMethod(String requestLine) {
        String[] urlSplit = requestLine.split(" ");
        return urlSplit[0];
    }

    public static String getUri(String requestLine) {
        String[] urlSplit = requestLine.split(" ");
        return urlSplit[1];
    }

    public static String getHttpVersion(String requestLine) {
        String[] urlSplit = requestLine.split(" ");
        return urlSplit[2];
    }

    public static String getRequestPath(String url) {
        int baseIndex = url.indexOf("?");
        return url.substring(0, baseIndex);
    }


    public static String getRequestParams(String url) {
        int baseIndex = url.indexOf("?");
        return url.substring(baseIndex+1);
    }
}
