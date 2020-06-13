package db;

import com.google.common.collect.Maps;
import domain.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataBase {
    private static Map<String, User> users = Maps.newHashMap();

    static {
        users.put("asd", new User("asd", "asd", null, null));
    }

    public static void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public static User findUserById(String userId) {
        return users.get(userId);
    }

    public static List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
