package next.controller.qna.question;

import core.mvc.*;
import next.controller.UserSessionUtils;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by jyami on 2020/09/05
 */
public class CreateQuestionFormController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        if(!UserSessionUtils.isLogined(request.getSession())){
            return jspView("redirect:/user/login");
        }
        User currentUser = UserSessionUtils.getUserFromSession(request.getSession());
        return jspView("/qna/form.jsp").addObject("user", currentUser);
    }
}
