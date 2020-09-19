package next.controller.qna.question;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.exception.CannotDeleteException;
import next.model.Answer;
import next.model.Question;

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
        Question question = QuestionDao.getInstance().findByQuestionId(questionId);
        List<Answer> answers = AnswerDao.getInstance().findAllByQuestionId(questionId);

        if(question == null){
            throw new CannotDeleteException("존재하지 않는 질문입니다.");
        }

        if (!UserSessionUtils.isSameUser(request.getSession(), question.getWriter())) {
            return createModelAndView(question, answers, "다른 사용자가 쓴 글을 삭제할 수 없습니다.");
        }

        if(answers.isEmpty()){
            QuestionDao.getInstance().delete(question);
            return jspView("redirect:/");
        }

        return createModelAndView(question, answers, "해당 글에 댓글이 존재해 삭제할 수 없습니다.");
    }

    private ModelAndView createModelAndView(Question question, List<Answer> answers, String errorMessgae){
        return jspView("/qna/show.jsp")
                .addObject("question", question)
                .addObject("answers", answers)
                .addObject("errorMessage", errorMessgae);

    }

}
