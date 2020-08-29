package controller;

import db.DataBase;
import domain.model.User;
import exception.UserNotFoundException;
import http.CysHttpRequest;
import http.CysHttpResponse;
import http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.extractor.BodyExtractor;

import java.io.IOException;
import java.util.Objects;

public class LoginController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);


    @Override
    public void service(CysHttpRequest httpRequest, CysHttpResponse httpResponse) throws IOException {
        User user = BodyExtractor.extract(User.class, httpRequest.getBodyString());
        HttpSession session = httpRequest.getSession();
        String response = login(session, user);
        httpResponse.response(response);
    }

    private String login(HttpSession session, User user) {
        User userById = DataBase.findUserById(user.getUserId());
        if (Objects.isNull(userById)) {
            throw new UserNotFoundException();
        }
        session.setAttribute("user", userById);
        return "index.html";
    }

}
