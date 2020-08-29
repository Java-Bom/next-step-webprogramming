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
import java.io.IOException;

@WebServlet("/users/update")
public class UpdateUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("user");

        if (sessionUser.isNotSameUserId(userId)) {
            throw new IllegalArgumentException("권한 없음");
        }

        User findUser = DataBase.findUserById(userId);
        req.setAttribute("user", findUser);
        RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionUser sessionUser = (SessionUser) req.getSession().getAttribute("user");
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));

        if (sessionUser.isNotSameUserId(user.getUserId())) {
            throw new IllegalArgumentException("권한 없음");
        }

        User findUser = DataBase.findUserById(sessionUser.getUserId());
        findUser.update(user.getEmail(), user.getName(), user.getPassword());
        DataBase.updateUser(findUser);
        resp.sendRedirect("/users");
    }
}
