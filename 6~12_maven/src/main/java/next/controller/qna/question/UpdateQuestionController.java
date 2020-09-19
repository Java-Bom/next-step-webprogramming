package next.controller.qna.question;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.exception.CannotDeleteException;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/09/19
 */
public class UpdateQuestionController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/user/login");
        }

        long questionId = Long.parseLong(request.getParameter("questionId"));
        Question question = QuestionDao.getInstance().findByQuestionId(questionId);

        if (question == null) {
            throw new CannotDeleteException("존재하지 않는 질문입니다.");
        }

        if (!UserSessionUtils.isSameUser(request.getSession(), question.getWriter())) {
            throw new IllegalStateException("다른 사용자가 쓴 글을 수정할 수 없습니다.");
        }

        question.update(new Question(question.getWriter(), request.getParameter("title"), request.getParameter("contents")));
        QuestionDao.getInstance().update(question);
        return jspView("redirect:/");
    }
}
