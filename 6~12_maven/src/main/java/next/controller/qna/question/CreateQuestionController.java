package next.controller.qna.question;

import core.mvc.*;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by jyami on 2020/09/05
 */
public class CreateQuestionController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/user/login");
        }
        User currentUser = UserSessionUtils.getUserFromSession(request.getSession());

        Question question = new Question(currentUser.getUserId(),
                request.getParameter("title"),
                request.getParameter("contents"));
        QuestionDao.getInstance().insert(question);

        return jspView("redirect:/");
    }
}
