package next.controller.user;

import core.web.Controller;
import core.web.JspView;
import core.web.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public ModelAndView execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        log.debug("logoutUser : {}", session.getAttribute("user"));
        session.removeAttribute("user");
        return new ModelAndView(new JspView("redirect:/"));
    }
}

