package util.extractor;

import util.HttpRequestUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

public class BodyExtractor {

    public static <T> T extract(Class<T> tClass, String bodyString) {
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
