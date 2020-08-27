package next.web;

import core.db.DataBase;
import next.model.CurrentUserChecker;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by jyami on 2020/08/26
 */
@WebServlet("/user/update")
public class UpdateUserFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> currentUser = CurrentUserChecker.getCurrentUser(req);
        if (!currentUser.isPresent()) {
            resp.sendRedirect("/user/login");
            return;
        }
        req.setAttribute("user", currentUser.get());
        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User currentUser = (User) req.getSession().getAttribute("user");

        currentUser.setUserId(req.getParameter("userId"));
        currentUser.setName(req.getParameter("name"));
        currentUser.setPassword(req.getParameter("password"));
        currentUser.setEmail(req.getParameter("email"));
        log.debug("user : {}", currentUser);

        DataBase.addUser(currentUser);
        resp.sendRedirect("/user/list");
    }
}
