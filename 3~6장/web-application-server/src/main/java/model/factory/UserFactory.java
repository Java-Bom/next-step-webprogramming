package model.factory;

import model.User;

import java.util.Map;

public final class UserFactory {
    public static User create(Map<String, String> queryMap) {
        User user = new User();

        user.setEmail(queryMap.get("email"));
        user.setName(queryMap.get("name"));
        user.setPassword(queryMap.get("password"));
        user.setUserId(queryMap.get("userId"));

        return user;
    }
}
