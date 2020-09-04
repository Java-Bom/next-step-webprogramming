package core.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private RequestMapping requestMapping;

    @Override
    public void init() throws ServletException {
        this.requestMapping = new RequestMapping();
    }

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        Controller controller = requestMapping.findController(uri);
        try {
            View view = controller.execute(req, resp);
            view.render(req, resp);
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

}
