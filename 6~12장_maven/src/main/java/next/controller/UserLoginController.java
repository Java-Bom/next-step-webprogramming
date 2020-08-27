package next.controller;

import core.db.DataBase;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Created by jyami on 2020/08/27
 */
public class UserLoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        Optional<User> user = DataBase.findUserById(userId);

        boolean successUserLogin = user.isPresent() && user.get().getPassword().equals(password);

        HttpSession session = request.getSession();
        if (successUserLogin) {
            session.setAttribute("loginFailed", false);
            session.setAttribute("user", user.get());
            return "redirect:/user/list";
        }else {
            session.setAttribute("loginFailed", true);
            return "/user/login.jsp";
        }
    }
}
