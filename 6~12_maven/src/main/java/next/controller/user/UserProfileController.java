package next.controller.user;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.View;
import next.dao.UserDao;
import next.model.CurrentUserChecker;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by jyami on 2020/09/05
 */
public class UserProfileController implements Controller {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> currentUser = CurrentUserChecker.getCurrentUser(request);
        if (!currentUser.isPresent()) {
            return new JspView("redirect:/user/login");
        }

        String userId = request.getParameter("userId");
        User user = new UserDao().findByUserId(userId);
        request.setAttribute("user", user);
        return new JspView("/user/profile.jsp");
    }
}
