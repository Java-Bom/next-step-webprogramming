package next.controller;

import core.db.DataBase;
import next.model.User;
import next.user.SessionUser;
import next.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserFormController implements Controller {
    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("user");

        if (sessionUser.isNotSameUserId(userId)) {
            throw new IllegalArgumentException("권한 없음");
        }

        User findUser = DataBase.findUserById(userId);
        request.setAttribute("user", findUser);
        return "/user/update.jsp";
    }
}
