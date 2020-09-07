package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.CurrentUserChecker;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/09/06
 */
public class AddAnswerController implements Controller {

    private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = CurrentUserChecker.getCurrentUser(request).get();
        Answer answer = new Answer(user.getUserId(), request.getParameter("contents"),
                Long.parseLong(request.getParameter("questionId")));
        log.debug("answer: {}", answer);

        AnswerDao answerDao = new AnswerDao();
        answerDao.insert(answer);

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(objectMapper.writeValueAsString(answer));
        return null;
    }
}
