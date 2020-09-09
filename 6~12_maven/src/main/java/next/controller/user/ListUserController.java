package next.controller.user;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.View;
import next.dao.UserDao;
import next.model.CurrentUserChecker;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Created by jyami on 2020/08/27
 */
public class ListUserController implements Controller {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> currentUser = CurrentUserChecker.getCurrentUser(request);
        if (!currentUser.isPresent()) {
            return new JspView("redirect:/user/login");
        }
        List<User> all = new UserDao().findAll();

        request.setAttribute("users", all);
        return new JspView("/user/list.jsp");
    }
}
