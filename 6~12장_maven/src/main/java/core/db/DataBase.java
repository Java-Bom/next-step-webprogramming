package core.db;

import com.google.common.collect.Maps;
import next.model.User;

import java.util.Collection;
import java.util.Map;

public class DataBase {
    private static Map<String, User> users = Maps.newHashMap();

    static {
        users.put("test", new User("test", "a", "a", "test@test"));
    }

    public static void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static Collection<User> findAll() {
        return users.values();
    }

    public static void updateUser(final User user) {
        users.put(user.getUserId(), user);
    }
}
