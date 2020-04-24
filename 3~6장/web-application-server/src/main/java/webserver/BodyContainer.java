package webserver;

import model.User;
import util.HttpRequestUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BodyContainer {
    private static final Map<String, Class<?>> BODY_PARSER;

    static {
        BODY_PARSER = new HashMap<>();
        BODY_PARSER.put("/user/create", User.class);
    }

    public static Class<?> findBy(String requestUrl){
        System.out.println(requestUrl);
        if(!BODY_PARSER.containsKey(requestUrl)){
            throw new RuntimeException();
        }
        return BODY_PARSER.get(requestUrl);
    }

    public static <T> T BodyContainer(Class<T> tClass, String bodyString) {
        Map<String, String> body = HttpRequestUtils.parseQueryString(bodyString);
        try {
            Constructor<T> constructor = tClass.getConstructor(null);
            T instance = constructor.newInstance();
            for (Map.Entry<String, String> entry : body.entrySet()) {
                Field field = tClass.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(instance, entry.getValue());
            }

            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

}
