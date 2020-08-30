package next.controller;

import next.dao.UserDao;
import next.user.SessionUser;
import next.web.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ListUserController.class);

    @Override
    public String execute(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        SessionUser user = (SessionUser) req.getSession().getAttribute("user");
        if (ObjectUtils.isEmpty(user)) {
            return "redirect:/users/login-form";
        }

        UserDao userDao = new UserDao();
        req.setAttribute("users", userDao.findAll());
        return "/user/list.jsp";
    }
}
