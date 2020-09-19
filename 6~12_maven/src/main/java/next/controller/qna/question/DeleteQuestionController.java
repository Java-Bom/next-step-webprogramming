package next.controller.qna.question;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.exception.CannotDeleteException;
import next.model.Answer;
import next.model.Question;
import next.service.QnaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jyami on 2020/09/19
 */
public class DeleteQuestionController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/user/login");
        }

        long questionId = Long.parseLong(request.getParameter("questionId"));
        try {
            QnaService.getInstance().deleteQuestion(questionId, UserSessionUtils.getUserFromSession(request.getSession()));
        } catch (CannotDeleteException ex) {
            Question question = QuestionDao.getInstance().findByQuestionId(questionId);
            List<Answer> answers = AnswerDao.getInstance().findAllByQuestionId(questionId);
            return createModelAndView(question, answers, ex.getMessage());
        }
        return jspView("redirect:/");
    }

    private ModelAndView createModelAndView(Question question, List<Answer> answers, String errorMessgae) {
        return jspView("/qna/show.jsp")
                .addObject("question", question)
                .addObject("answers", answers)
                .addObject("errorMessage", errorMessgae);
    }
}
