package core.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspView implements View {
    private static final String REDIRECT_PREFIX = "redirect:";

    private final String url;

    public JspView(final String url) {
        this.url = url;
    }

    @Override
    public void render(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        if (url.startsWith(REDIRECT_PREFIX)) {
            response.sendRedirect(url.substring(REDIRECT_PREFIX.length()));
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }
}
