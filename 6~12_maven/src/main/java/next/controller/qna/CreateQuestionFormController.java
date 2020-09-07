package next.controller.qna;

import core.mvc.Controller;
import next.model.CurrentUserChecker;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by jyami on 2020/09/05
 */
public class CreateQuestionFormController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> currentUser = CurrentUserChecker.getCurrentUser(request);
        if (!currentUser.isPresent()) {
            return "redirect:/user/login";
        }
        request.setAttribute("user", currentUser.get());
        return "/qna/form.jsp";
    }
}
