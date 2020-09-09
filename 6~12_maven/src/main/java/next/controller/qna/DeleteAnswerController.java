package next.controller.qna;

import core.jdbc.DataAccessException;
import core.mvc.Controller;
import core.mvc.JsonView;
import core.mvc.View;
import next.dao.AnswerDao;
import next.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/09/08
 */
public class DeleteAnswerController implements Controller {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long answerId = Long.parseLong(request.getParameter("answerId"));
        try{
            new AnswerDao().delete(answerId);
            request.setAttribute("result", Result.ok());
        }catch (DataAccessException e){
            request.setAttribute("result", Result.fail(e.getMessage()));
        }
        return new JsonView();
    }
}
