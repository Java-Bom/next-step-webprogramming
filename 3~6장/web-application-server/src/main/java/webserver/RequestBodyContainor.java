package webserver;

import model.factory.UserFactory;
import util.HttpRequestUtils;

import java.util.Map;

public class RequestBodyContainor {
    private final String body;
    private final RequestBodyType requestBodyType;


    public RequestBodyContainor(final String body, final String url) {
        this.body = body;
        this.requestBodyType = RequestBodyType.findByUrl(url);
    }

    public <T> T parse(Class<T> tClass) {
        requestBodyType.checkType(tClass);
        Map<String, String> queryMap = HttpRequestUtils.parseQueryString(body);
        return tClass.cast(UserFactory.create(queryMap));
    }

}
