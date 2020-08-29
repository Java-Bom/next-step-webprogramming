package next.controller;

import next.dao.UserDao;
import next.model.User;
import next.user.SessionUser;
import next.web.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LoginUserController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        User loginUser = null;
        try {
            UserDao userDao = new UserDao();
            loginUser = userDao.findByUserId(request.getParameter("userId"));
            loginUser.checkPassword(request.getParameter("password"));
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (IllegalArgumentException e) {
            return "redirect:/user/login_failed.jsp";
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", new SessionUser(loginUser.getUserId(), loginUser.getEmail()));
        log.debug("loginUser : {}", loginUser);
        return "redirect:/";
    }
}

