package next.controller;

import core.mvc.*;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/09/05
 */
public class HomeController extends AbstractController {

    private QuestionDao questionDao = QuestionDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        return jspView("home.jsp").addObject("questions", questionDao.findAll());
    }
}
