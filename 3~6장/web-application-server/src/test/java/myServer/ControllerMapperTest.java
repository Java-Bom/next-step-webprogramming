package myServer;

import myServer.controller.UserController;
import myServer.httpRequest.HttpRequestEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by jyami on 2020/04/30
 */
class ControllerMapperTest {

    private HttpRequestEntity makeHttptRequestEntity(String statusLine){
        ByteArrayInputStream in = new ByteArrayInputStream(statusLine.getBytes());
        return new HttpRequestEntity(in);
    }

    @Test
    @DisplayName("UserController 매칭 테스트")
    void executeController() {
        HttpRequestEntity httpRequestEntity = makeHttptRequestEntity("POST /user/create HTTP/1.1");
        Class<?> userControllerClass = ControllerMapper.executeController(httpRequestEntity);
        assertThat(userControllerClass.getSimpleName()).isEqualTo("UserController");
        assertThat(userControllerClass).isEqualTo(UserController.class);
    }

    @Test
    @DisplayName("UserController 매칭 X 테스트")
    void executeControllerNotMatchName() {
        HttpRequestEntity httpRequestEntity = makeHttptRequestEntity("POST /hello/create HTTP/1.1");
        assertThatThrownBy(
                () -> ControllerMapper.executeController(httpRequestEntity)
        ).isInstanceOf(RuntimeException.class);
    }
}