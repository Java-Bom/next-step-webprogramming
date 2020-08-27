package core.db;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.Maps;

import next.model.User;

public class DataBase {
    private static Map<String, User> users = Maps.newHashMap();

    public static void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public static Optional<User> findUserById(String userId) {
        return users.containsKey(userId) ? Optional.of(users.get(userId)) : Optional.empty();
    }

    public static Collection<User> findAll() {
        return users.values();
    }
}
