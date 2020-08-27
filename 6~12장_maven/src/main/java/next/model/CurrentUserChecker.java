package next.model;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by jyami on 2020/08/27
 */
public class CurrentUserChecker {
    public static Optional<User> getCurrentUser(HttpServletRequest req) {
        Object currentUser = req.getSession().getAttribute("user");
        if (currentUser == null)
            return Optional.empty();
        return Optional.of((User) currentUser);
    }
}
