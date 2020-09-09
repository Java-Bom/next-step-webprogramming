package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/09/09
 */
public class JspView implements View {

    private String viewName;
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    public JspView(String viewName) {
        if(viewName == null){
            throw new NullPointerException("viewName is Null, 이동할 URL을 추가해주세요");
        }
        this.viewName = viewName;
    }

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            response.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return;
        }
        RequestDispatcher rd = request.getRequestDispatcher(viewName);
        rd.forward(request, response);
    }
}
