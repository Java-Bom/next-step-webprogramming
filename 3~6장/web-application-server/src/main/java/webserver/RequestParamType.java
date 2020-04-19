package webserver;

import model.User;
import model.factory.UserFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum RequestParamType {
    USER("/user/create", User.class, UserFactory::create);

    private final String path;
    private Class<?> type;
    private Function<Map<String, String>, ?> factoryFunction;

    RequestParamType(final String path, final Class<?> type, final Function<Map<String, String>, ?> factoryFunction) {
        this.path = path;
        this.type = type;
        this.factoryFunction = factoryFunction;
    }

    public static RequestParamType findType(String path) {
        return Arrays.stream(values())
                .filter(type -> type.path.equals(path))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public Function<Map<String, String>, ?> getFactoryFunction() {
        return factoryFunction;
    }

    public <T> T convertModel(Class<T> type, Map<String, String> queryMap) {
        if (this.type.equals(type)) {
            return type.cast(factoryFunction.apply(queryMap));
        }
        throw new RuntimeException();
    }
}
