package webserver;

import util.HttpRequestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpHeaderContainer {
    private List<HttpRequestUtils.Pair> container = new ArrayList<>();
    private Map<String,String> body = new HashMap<>();

    public void extractHeader(BufferedReader br) throws IOException {
        String line = br.readLine();
        while (!"".equals(line)) {
            HttpRequestUtils.Pair pair = HttpRequestUtils.parseHeader(line);
            container.add(pair);
            line = br.readLine();
        }
    }

    public int getContentLength(){
        return Integer.parseInt(container.stream()
                .filter(pair -> pair.getKey().equals("Content-Length"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getValue());
    }
}
