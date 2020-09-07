package next.controller.qna;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.CurrentUserChecker;
import next.model.Question;
import next.model.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Created by jyami on 2020/09/05
 */
public class ShowController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        Optional<User> currentUser = CurrentUserChecker.getCurrentUser(request);
        if (!currentUser.isPresent()) {
            return "redirect:/user/login";
        }

        long questionId = Long.parseLong(request.getParameter("questionId"));
        Question question = new QuestionDao().findByQuestionId(questionId);
        List<Answer> answers = new AnswerDao().findAllByQuestionId(questionId);
        request.setAttribute("question", question);
        request.setAttribute("answers", answers);
        return "/qna/show.jsp";
    }
}
