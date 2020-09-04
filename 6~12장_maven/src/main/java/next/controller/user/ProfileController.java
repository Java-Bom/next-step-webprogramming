package next.controller.user;

import core.web.Controller;
import core.web.JspView;
import core.web.View;
import next.dao.UserDao;
import next.model.User;
import next.user.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ProfileController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    @Override
    public View execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        Optional<SessionUser> sessionUser = Optional.ofNullable((SessionUser) request.getSession().getAttribute("user"));

        if (!sessionUser.isPresent()) {
            return new JspView("redirect:/user/login_failed.jsp");
        }

        User user = null;
        UserDao userDao = new UserDao();
        user = userDao.findById(sessionUser.get().getUserId());

        if (user == null) {
            throw new NullPointerException("사용자를 찾을 수 없습니다.");
        }
        request.setAttribute("user", user);
        return new JspView("/user/profile.jsp");
    }
}
