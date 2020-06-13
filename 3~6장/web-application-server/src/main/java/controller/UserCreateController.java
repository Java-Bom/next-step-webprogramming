package controller;

import db.DataBase;
import domain.model.User;
import http.CysHttpRequest;
import http.CysHttpResponse;
import util.extractor.BodyExtractor;

public class UserCreateController implements Controller {

    @Override
    public void service(CysHttpRequest httpRequest, CysHttpResponse httpResponse) {
        String body = httpRequest.getBodyString();
        User user = BodyExtractor.extract(User.class, body);
        String page = create(user);
        httpResponse.responseRedirect(page);
    }

    private String create(User user) {
        DataBase.addUser(user);
        return "index.html";
    }
}
