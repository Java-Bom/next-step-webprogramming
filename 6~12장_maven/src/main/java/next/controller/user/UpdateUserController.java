package next.controller.user;

import core.web.Controller;
import core.web.JspView;
import core.web.ModelAndView;
import next.dao.UserDao;
import next.model.User;
import next.user.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public ModelAndView execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");
        User user = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"),
                request.getParameter("email"));

        if (sessionUser.isNotSameUserId(user.getUserId())) {
            throw new IllegalArgumentException("권한 없음");
        }

        UserDao userDao = new UserDao();
        User findUser = userDao.findById(sessionUser.getUserId());
        findUser.update(user.getEmail(), user.getName(), user.getPassword());
        userDao.update(findUser);

        return new ModelAndView(new JspView("redirect:/users"));
    }
}
