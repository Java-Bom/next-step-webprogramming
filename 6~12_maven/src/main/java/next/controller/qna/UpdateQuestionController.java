package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.CurrentUserChecker;
import next.model.Question;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by jyami on 2020/09/19
 */
public class UpdateQuestionController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Optional<User> currentUserOptional = CurrentUserChecker.getCurrentUser(request);
        if (!currentUserOptional.isPresent()) {
            return jspView("redirect:/user/login");
        }

        long questionId = Long.parseLong(request.getParameter("questionId"));
        Question question = QuestionDao.getInstance().findByQuestionId(questionId);
        if (!question.getWriter().equals(currentUserOptional.get().getUserId())) {
            throw new IllegalStateException("다른 사용자가 쓴 글을 수정할 수 없습니다.");
        }

        question.update(new Question(question.getWriter(), request.getParameter("title"), request.getParameter("contents")));
        QuestionDao.getInstance().update(question);
        return jspView("redirect:/");
    }
}
