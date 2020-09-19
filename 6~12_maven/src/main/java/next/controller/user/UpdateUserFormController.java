package next.controller.user;

import core.mvc.*;
import next.controller.UserSessionUtils;
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
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/user/login");
        }
        User currentUser = UserSessionUtils.getUserFromSession(request.getSession());
        return jspView("/user/updateForm.jsp").addObject("user", currentUser);
    }
}
