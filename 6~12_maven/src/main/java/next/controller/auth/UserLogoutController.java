package next.controller.auth;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by jyami on 2020/08/27
 */
public class UserLogoutController implements Controller {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return new JspView("redirect:/");
    }
}
