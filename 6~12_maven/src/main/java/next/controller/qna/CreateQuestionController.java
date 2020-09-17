package next.controller.qna;

import core.mvc.*;
import next.dao.QuestionDao;
import next.model.CurrentUserChecker;
import next.model.Question;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by jyami on 2020/09/05
 */
public class CreateQuestionController extends AbstractController {

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {

        Optional<User> currentUser = CurrentUserChecker.getCurrentUser(request);
        if (!currentUser.isPresent()) {
            return jspView("redirect:/user/login");
        }

        Question question = new Question(currentUser.get().getUserId(),
                request.getParameter("title"),
                request.getParameter("contents"));
        questionDao.insert(question);

        return jspView("redirect:/");
    }
}
