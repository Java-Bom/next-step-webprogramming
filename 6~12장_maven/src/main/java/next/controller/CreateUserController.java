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

/**
 * Created by jyami on 2020/08/27
 */
public class CreateUserController implements Controller {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new User(request.getParameter("userId"), request.getParameter("password"),
                request.getParameter("name"), request.getParameter("email"));

        UserDao userDao = new UserDao();
        try {
            userDao.insert(user);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "redirect:/user/list";
    }
}
