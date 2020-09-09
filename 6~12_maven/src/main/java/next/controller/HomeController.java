package next.controller;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.View;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/09/05
 */
public class HomeController implements Controller {
    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) {
        QuestionDao questionDao = new QuestionDao();
        request.setAttribute("questions", questionDao.findAll());
        return new JspView("home.jsp");
    }
}
