package next.web;

import core.db.DataBase;
import next.model.User;
import next.user.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = DataBase.findUserById(req.getParameter("userId"));
        try {
            loginUser.checkPassword(req.getParameter("password"));
        } catch (IllegalArgumentException e) {
            resp.sendRedirect("/user/login_failed.html");
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", new SessionUser(loginUser.getUserId()));
        log.debug("loginUser : {}", loginUser);
        resp.sendRedirect("/");
    }
}

