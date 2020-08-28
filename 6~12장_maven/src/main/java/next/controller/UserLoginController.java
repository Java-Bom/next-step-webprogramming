package next.controller;

import core.db.DataBase;
import next.dao.UserDao;
import next.model.User;
import next.web.DispatcherServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created by jyami on 2020/08/27
 */
public class UserLoginController implements Controller {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = new UserDao().findByUserId(userId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        boolean successUserLogin = user != null && user.getPassword().equals(password);

        HttpSession session = request.getSession();
        if (successUserLogin) {
            session.setAttribute("loginFailed", false);
            session.setAttribute("user", user);
            return "redirect:/user/list";
        } else {
            session.setAttribute("loginFailed", true);
            return "/user/login.jsp";
        }
    }
}
