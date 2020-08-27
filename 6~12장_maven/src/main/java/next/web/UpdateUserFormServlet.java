package next.web;

import core.db.DataBase;
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
@WebServlet("/user/update/*")
public class UpdateUserFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getPathInfo().replace("/", "");
        Optional<User> userById = DataBase.findUserById(userId);
        if (!userById.isPresent()) {
            resp.sendRedirect("/user/create");
            return;
        }
        req.setAttribute("user", userById.get());
        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getPathInfo().replace("/", "");
        User user = DataBase.findUserById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저"));

        user.setUserId(req.getParameter("userId"));
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        log.debug("user : {}", user);

        DataBase.addUser(user);
        resp.sendRedirect("/user/list");
    }
}
