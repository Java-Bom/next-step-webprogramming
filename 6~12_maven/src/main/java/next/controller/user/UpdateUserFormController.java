package next.controller.user;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.View;
import next.model.CurrentUserChecker;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by jyami on 2020/08/27
 */
public class UpdateUserFormController implements Controller {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> currentUser = CurrentUserChecker.getCurrentUser(request);
        if (!currentUser.isPresent()) {
            return new JspView("redirect:/user/login");
        }
        request.setAttribute("user", currentUser.get());
        return new JspView("/user/updateForm.jsp");
    }
}
