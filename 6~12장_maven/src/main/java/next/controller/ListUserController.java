package next.controller;

import core.db.DataBase;
import next.dao.UserDao;
import next.model.CurrentUserChecker;
import next.model.User;
import next.web.DispatcherServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by jyami on 2020/08/27
 */
public class ListUserController implements Controller {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> currentUser = CurrentUserChecker.getCurrentUser(request);
        if (!currentUser.isPresent()) {
            return "redirect:/user/login";
        }
        List<User> all = new ArrayList<>();
        try {
            all = new UserDao().findAll();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        request.setAttribute("users", all);
        return "/user/list.jsp";
    }
}
