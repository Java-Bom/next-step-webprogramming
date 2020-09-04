package next.controller.user;

import core.web.Controller;
import core.web.JspView;
import core.web.View;
import next.dao.UserDao;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public View execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        User user = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"),
                request.getParameter("email"));
        log.debug("user : {}", user);
        UserDao userDao = new UserDao();
        userDao.insert(user);
        return new JspView("redirect:/users");
    }
}
