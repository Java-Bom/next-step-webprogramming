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

public class UpdateUserFormController implements Controller {
    private final Logger log = LoggerFactory.getLogger(UpdateUserFormController.class);

    @Override
    public View execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");

        if (sessionUser.isNotSameUserId(userId)) {
            throw new IllegalArgumentException("권한 없음");
        }

        User findUser = null;
        UserDao userDao = new UserDao();
        findUser = userDao.findById(sessionUser.getUserId());
        request.setAttribute("user", findUser);
        return new JspView("/user/update.jsp");
    }
}
