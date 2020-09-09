package next.controller.qna;

import core.mvc.Controller;
import core.mvc.JsonView;
import core.mvc.View;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.CurrentUserChecker;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/09/06
 */
public class AddAnswerController implements Controller {

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = CurrentUserChecker.getCurrentUser(request).get();
        Answer answer = new Answer(user.getUserId(), request.getParameter("contents"),
                Long.parseLong(request.getParameter("questionId")));

        AnswerDao answerDao = new AnswerDao();
        answerDao.insert(answer);

        request.setAttribute("answer", answer);
        return new JsonView();
    }
}
