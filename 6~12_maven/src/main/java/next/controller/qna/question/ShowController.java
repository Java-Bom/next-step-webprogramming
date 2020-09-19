package next.controller.qna.question;

import core.mvc.*;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Created by jyami on 2020/09/05
 */
public class ShowController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/user/login");
        }

        long questionId = Long.parseLong(request.getParameter("questionId"));

        Question question = QuestionDao.getInstance().findByQuestionId(questionId);
        List<Answer> answers = AnswerDao.getInstance().findAllByQuestionId(questionId);

        return jspView("/qna/show.jsp").addObject("answers", answers).addObject("question", question);
    }
}
