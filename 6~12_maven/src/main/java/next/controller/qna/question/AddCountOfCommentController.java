package next.controller.qna.question;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/09/17
 */
public class AddCountOfCommentController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        long questionId = Long.parseLong(request.getParameter("questionId"));
        int countOfComment = QuestionDao.getInstance().addCountOfComment(questionId);
        return jsonView().addObject("countOfComment", countOfComment);
    }
}
