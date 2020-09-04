package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import next.dao.AnswerDao;
import next.dto.AnswerResponse;
import next.model.Answer;
import next.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AddAnswerController implements Controller {
    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        Answer answer = new Answer(
                request.getParameter("writer"),
                request.getParameter("contents"),
                Long.parseLong(request.getParameter("questionId"))
        );

        AnswerDao answerDao = new AnswerDao();
        Answer savedAnswer = answerDao.insert(answer);
        AnswerResponse answerResponse = new AnswerResponse(savedAnswer);
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(objectMapper.writeValueAsString(answerResponse));
        return null;
    }
}
