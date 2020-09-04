package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import next.dao.AnswerDao;
import next.model.Result;
import next.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class DeleteAnswerController implements Controller {
    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        Long answerId = Long.parseLong(request.getParameter("answerId"));
        AnswerDao answerDao = new AnswerDao();
        answerDao.delete(answerId);

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(objectMapper.writeValueAsString(Result.ok()));
        return null;
    }
}
