package next.controller.user;

import core.mvc.Controller;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> currentUser = CurrentUserChecker.getCurrentUser(request);
        if (!currentUser.isPresent()) {
            return "redirect:/user/login";
        }
        request.setAttribute("user", currentUser.get());
        return "/user/updateForm.jsp";
    }
}
