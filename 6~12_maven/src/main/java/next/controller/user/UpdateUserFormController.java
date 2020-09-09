package next.controller.user;

import core.mvc.*;
import next.model.CurrentUserChecker;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by jyami on 2020/08/27
 */
public class UpdateUserFormController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> currentUser = CurrentUserChecker.getCurrentUser(request);
        if (!currentUser.isPresent()) {
            return jspView("redirect:/user/login");
        }
        return jspView("/user/updateForm.jsp").addObject("user", currentUser.get());
    }
}
