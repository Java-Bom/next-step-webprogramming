package next.controller.qna;

import core.web.Controller;
import core.web.JsonView;
import core.web.View;
import next.dao.AnswerDao;
import next.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswerController implements Controller {
    @Override
    public View execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        Long answerId = Long.parseLong(request.getParameter("answerId"));
        AnswerDao answerDao = new AnswerDao();
        answerDao.delete(answerId);

        return new JsonView(Result.ok());
    }
}
