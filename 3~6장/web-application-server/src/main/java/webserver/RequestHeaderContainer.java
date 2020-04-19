package webserver;

import dto.RequestParamDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RequestHeaderContainer {
    private final List<Header> container = new ArrayList<>();

    public void add(String line) {
        int number = container.size();
        container.add(new Header(number, line));
    }

    public String getUrl() {
        if (container.isEmpty()) {
            throw new RuntimeException("헤더가 없습니다.");
        }

        return container.get(0).getUrl();
    }

    public boolean hasQuery() {
        return getUrl().contains("?");
    }

    public boolean isPost() {
        return "POST".equals(getMethod());
    }

    public String getMethod() {
        return container.get(0).getMethod();
    }


    public RequestParamDto getQueryString() {
        String url = getUrl();
        int index = url.indexOf("?");
        String requestPath = url.substring(0, index);
        String params = url.substring(index + 1);
        return new RequestParamDto(requestPath, params);
    }


    static class Header {
        private Integer lineNumber;
        private List<String> tokens;

        public Header(Integer number, String line) {
            this.lineNumber = number;
            this.tokens = Arrays.stream(line.split(" "))
                    .collect(Collectors.toList());
        }

        public String getUrl() {
            if (lineNumber != 0) {
                throw new RuntimeException();
            }

            return tokens.get(1);
        }

        public String getMethod() {
            if (lineNumber != 0) {
                throw new RuntimeException();
            }

            return tokens.get(0);
        }
    }

}
