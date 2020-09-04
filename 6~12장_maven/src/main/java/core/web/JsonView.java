package core.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JsonView implements View {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Object data;

    public JsonView(final Object data) {
        this.data = data;
    }

    @Override
    public void render(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(objectMapper.writeValueAsString(data));
    }
}
