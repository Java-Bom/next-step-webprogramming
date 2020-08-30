package next.controller.user;

import next.controller.Controller;
import next.dao.UserDao;
import next.model.CurrentUserChecker;
import next.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by jyami on 2020/08/27
 */
public class UpdateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> currentUserOptional = CurrentUserChecker.getCurrentUser(request);
        if (!currentUserOptional.isPresent()) {
            return "redirect:/user/login";
        }
        User currentUser = currentUserOptional.get();
        currentUser.setUserId(request.getParameter("userId"));
        currentUser.setName(request.getParameter("name"));
        currentUser.setPassword(request.getParameter("password"));
        currentUser.setEmail(request.getParameter("email"));

        new UserDao().update(currentUser);

        return "redirect:/user/list";

    }
}
