package next.controller;

import core.db.DataBase;
import next.user.SessionUser;
import next.web.Controller;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        SessionUser user = (SessionUser) req.getSession().getAttribute("user");
        if (ObjectUtils.isEmpty(user)) {
            return "redirect:/users/login";
        }

        req.setAttribute("users", DataBase.findAll());
        return "/user/list.jsp";
    }
}
