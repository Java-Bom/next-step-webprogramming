package next.controller.qna.question;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.exception.CannotDeleteException;
import next.model.Result;
import next.service.QnaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/09/19
 */
public class ApiDeleteQuestionController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return jspView("redirect:/user/login");
        }

        try {
            long questionId = Long.parseLong(request.getParameter("questionId"));
            QnaService.getInstance().deleteQuestion(questionId, UserSessionUtils.getUserFromSession(request.getSession()));
            return jsonView().addObject("result", Result.ok());
        } catch (CannotDeleteException ex) {
            return jsonView().addObject("result", Result.fail(ex.getMessage()));
        }
    }
}
