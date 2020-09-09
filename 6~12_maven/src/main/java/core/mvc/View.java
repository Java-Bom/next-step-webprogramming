package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/09/09
 */
public interface View {
    void render(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
