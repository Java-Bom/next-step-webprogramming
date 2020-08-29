package next.controller;

import core.db.DataBase;
import next.model.User;
import next.user.SessionUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/users/profile")
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<SessionUser> sessionUser = Optional.ofNullable((SessionUser) req.getSession().getAttribute("user"));

        if (!sessionUser.isPresent()) {
            resp.sendRedirect("/user/login_failed.jsp");
            return;
        }

        User user = DataBase.findUserById(sessionUser.get().getUserId());
        if (user == null) {
            throw new NullPointerException("사용자를 찾을 수 없습니다.");
        }
        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/user/profile.jsp");
        rd.forward(req, resp);
    }
}
