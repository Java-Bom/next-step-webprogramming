package next.controller;

import next.dao.UserDao;
import next.model.User;
import next.user.SessionUser;
import next.web.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UpdateUserController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");
        User user = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"),
                request.getParameter("email"));

        if (sessionUser.isNotSameUserId(user.getUserId())) {
            throw new IllegalArgumentException("권한 없음");
        }

        try {
            UserDao userDao = new UserDao();
            User findUser = userDao.findByUserId(sessionUser.getUserId());
            findUser.update(user.getEmail(), user.getName(), user.getPassword());
            userDao.update(findUser);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return "redirect:/users";
    }
}
