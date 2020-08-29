package next.controller;

import core.db.DataBase;
import next.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        request.setAttribute("users", DataBase.findAll());
        return "index.jsp";
    }

}
