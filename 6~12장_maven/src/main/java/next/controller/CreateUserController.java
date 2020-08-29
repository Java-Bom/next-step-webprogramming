package next.controller;

import core.db.DataBase;
import next.model.User;
import next.web.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        User user = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"),
                request.getParameter("email"));
        log.debug("user : {}", user);
        DataBase.addUser(user);
        return "redirect:/user/list";
    }
}
