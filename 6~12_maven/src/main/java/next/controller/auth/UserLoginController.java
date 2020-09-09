package next.controller.auth;

import core.mvc.*;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by jyami on 2020/08/27
 */
public class UserLoginController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        User user = new UserDao().findByUserId(userId);

        boolean successUserLogin = user != null && user.getPassword().equals(password);

        HttpSession session = request.getSession();
        if (successUserLogin) {
            session.setAttribute("loginFailed", false);
            session.setAttribute("user", user);
            return jspView("redirect:/");
        } else {
            session.setAttribute("loginFailed", true);
            return jspView("/user/login.jsp");
        }
    }
}
