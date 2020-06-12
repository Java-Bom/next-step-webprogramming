package converter;

import model.User;

import java.util.Map;

public class UserConverter {
    public static User convert(Map<String, String> params) {
        return new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
    }
}
