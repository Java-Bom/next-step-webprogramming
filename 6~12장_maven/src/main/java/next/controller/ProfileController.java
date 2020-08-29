package next.controller;

import core.db.DataBase;
import next.model.User;
import next.user.SessionUser;
import next.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ProfileController implements Controller {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        Optional<SessionUser> sessionUser = Optional.ofNullable((SessionUser) request.getSession().getAttribute("user"));

        if (!sessionUser.isPresent()) {
            return "redirect:/user/login_failed.jsp";
        }

        User user = DataBase.findUserById(sessionUser.get().getUserId());
        if (user == null) {
            throw new NullPointerException("사용자를 찾을 수 없습니다.");
        }
        request.setAttribute("user", user);
        return "/user/profile.jsp";
    }
}
