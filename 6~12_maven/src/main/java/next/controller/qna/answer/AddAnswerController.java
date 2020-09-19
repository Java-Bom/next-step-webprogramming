package next.controller.qna.answer;

import core.mvc.*;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.Result;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/09/06
 */
public class AddAnswerController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jsonView().addObject("result", Result.fail("Login is required"));
        }
        User user = UserSessionUtils.getUserFromSession(request.getSession());

        Answer answer = new Answer(user.getUserId(), request.getParameter("contents"),
                Long.parseLong(request.getParameter("questionId")));
        AnswerDao.getInstance().insert(answer);

        return jsonView().addObject("answer", answer);
    }
}
