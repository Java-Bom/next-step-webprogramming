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
public class UpdateQuestionFormController extends AbstractController {

    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Optional<User> currentUserOptional = CurrentUserChecker.getCurrentUser(request);
        if (!currentUserOptional.isPresent()) {
            return jspView("redirect:/user/login");
        }

        String writer = request.getParameter("writer");
        if (!currentUserOptional.get().getUserId().equals(writer)) {
            throw new IllegalStateException("다른 사용자가 쓴 글을 수정할 수 없습니다.");
        }

        long questionId = Long.parseLong(request.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);
        return jspView("/qna/updateForm.jsp").addObject("question", question)
                .addObject("writer", writer);
    }
}
