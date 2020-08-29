package next.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {
    private final String forwardUrl;

    public ForwardController(final String forwardUrl) {
        this.forwardUrl = forwardUrl;
        if (this.forwardUrl == null) {
            throw new IllegalArgumentException("포워딩할 url 없음");
        }
    }

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return forwardUrl;
    }
}
