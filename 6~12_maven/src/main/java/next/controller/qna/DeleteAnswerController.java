package next.controller.qna;

import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/09/08
 */
public class DeleteAnswerController extends AbstractController {

    private AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long answerId = Long.parseLong(request.getParameter("answerId"));
        try{
            answerDao.delete(answerId);
            return jsonView().addObject("result", Result.ok());
        }catch (DataAccessException e){
            return jsonView().addObject("result", Result.fail(e.getMessage()));
        }
    }
}
