package next.controller;

import core.db.DataBase;
import next.model.User;
import next.user.SessionUser;
import next.web.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        User findUser = DataBase.findUserById(sessionUser.getUserId());
        findUser.update(user.getEmail(), user.getName(), user.getPassword());
        DataBase.updateUser(findUser);
        return "redirect:/users";
    }
}
