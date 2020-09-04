package core.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JsonView implements View {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void render(final Map<String, ?> model, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(objectMapper.writeValueAsString(model));
    }

    private Map<String, Object> createModel(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getAttributeNames();
        Map<String, Object> model = new HashMap<>();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            model.put(name, request.getAttribute(name));
        }
        return model;
    }
}
