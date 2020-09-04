package next.controller.qna;

import core.web.Controller;
import core.web.JsonView;
import core.web.ModelAndView;
import next.dao.AnswerDao;
import next.dto.AnswerResponse;
import next.model.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAnswerController implements Controller {
    @Override
    public ModelAndView execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        Answer answer = new Answer(
                request.getParameter("writer"),
                request.getParameter("contents"),
                Long.parseLong(request.getParameter("questionId"))
        );

        AnswerDao answerDao = new AnswerDao();
        Answer savedAnswer = answerDao.insert(answer);
        AnswerResponse answerResponse = new AnswerResponse(savedAnswer);
        request.setAttribute("answer", answerResponse);
        return new ModelAndView(new JsonView());
    }
}
