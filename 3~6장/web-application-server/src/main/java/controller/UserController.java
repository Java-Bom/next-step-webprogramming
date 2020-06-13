package controller;

import db.DataBase;
import domain.model.User;
import webserver.exception.UserNotFoundException;

import java.util.List;
import java.util.Objects;

public class UserController {

    public String create(User user) {
        DataBase.addUser(user);
        return "index.html";
    }

    public String login(User user) {
        User userById = DataBase.findUserById(user.getUserId());
        if (Objects.isNull(userById)) {
            throw new UserNotFoundException();
        }
        return "index.html";
    }

    public String getUsers() {
        List<User> users = DataBase.findAll();
        StringBuilder builder = new StringBuilder();
        for (User user : users) {
            builder.append(user.toString() + "\n");
        }
        return builder.toString();
    }
}
