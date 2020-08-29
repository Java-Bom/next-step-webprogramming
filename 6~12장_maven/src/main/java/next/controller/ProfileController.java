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
import java.util.Optional;

public class ProfileController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        Optional<SessionUser> sessionUser = Optional.ofNullable((SessionUser) request.getSession().getAttribute("user"));

        if (!sessionUser.isPresent()) {
            return "redirect:/user/login_failed.jsp";
        }

        User user = null;
        try {
            UserDao userDao = new UserDao();
            user = userDao.findByUserId(sessionUser.get().getUserId());
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        if (user == null) {
            throw new NullPointerException("사용자를 찾을 수 없습니다.");
        }
        request.setAttribute("user", user);
        return "/user/profile.jsp";
    }
}
