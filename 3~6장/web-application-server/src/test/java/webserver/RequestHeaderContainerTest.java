package webserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RequestHeaderContainerTest {

    @DisplayName("라인별 요청헤더에서 url를 찾아준다")
    @Test
    void add() {
        //given
        List<String> headers = Arrays.asList("GET TEST", "NEW");

        //when
        RequestHeaderContainer container = new RequestHeaderContainer();
        headers.forEach(container::add);

        //then
        String url = container.getUrl();
        assertThat(url).isEqualTo("TEST");
    }
}