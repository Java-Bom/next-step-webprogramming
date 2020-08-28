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
import java.util.Optional;

/**
 * Created by jyami on 2020/08/27
 */
public class UpdateUserController implements Controller {

    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> currentUserOptional = CurrentUserChecker.getCurrentUser(request);
        if(!currentUserOptional.isPresent()){
            return "redirect:/user/login";
        }
        User currentUser = currentUserOptional.get();
        currentUser.setUserId(request.getParameter("userId"));
        currentUser.setName(request.getParameter("name"));
        currentUser.setPassword(request.getParameter("password"));
        currentUser.setEmail(request.getParameter("email"));

        try {
            new UserDao().update(currentUser);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return "redirect:/user/list";

    }
}
