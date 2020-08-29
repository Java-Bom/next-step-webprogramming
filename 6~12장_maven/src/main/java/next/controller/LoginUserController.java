package next.controller;

import core.db.DataBase;
import next.model.User;
import next.user.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/users/login")
public class LoginUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = DataBase.findUserById(req.getParameter("userId"));
        try {
            loginUser.checkPassword(req.getParameter("password"));
        } catch (IllegalArgumentException e) {
            resp.sendRedirect("/user/login_failed.jsp");
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", new SessionUser(loginUser.getUserId(), loginUser.getEmail()));
        log.debug("loginUser : {}", loginUser);
        resp.sendRedirect("/");
    }
}

