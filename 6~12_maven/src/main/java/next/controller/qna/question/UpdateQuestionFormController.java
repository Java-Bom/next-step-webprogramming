package next.controller.qna.question;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jyami on 2020/09/19
 */
public class UpdateQuestionFormController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/user/login");
        }

        String writer = request.getParameter("writer");
        long questionId = Long.parseLong(request.getParameter("questionId"));
        Question question = QuestionDao.getInstance().findByQuestionId(questionId);

        if (!UserSessionUtils.isSameUser(request.getSession(), question.getWriter())) {
            return createModelAndView(question, AnswerDao.getInstance().findAllByQuestionId(questionId),
                    "다른 사용자가 쓴 글을 수정할 수 없습니다.");
        }

        return jspView("/qna/updateForm.jsp").addObject("question", question)
                .addObject("writer", writer);
    }

    private ModelAndView createModelAndView(Question question, List<Answer> answers, String errorMessgae) {
        return jspView("/qna/show.jsp")
                .addObject("question", question)
                .addObject("answers", answers)
                .addObject("errorMessage", errorMessgae);
    }
}
