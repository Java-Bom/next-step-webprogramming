package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/08/27
 */
public class ForwardController implements Controller {

    private String forwardUrl;

    public ForwardController(String forwardUrl) {
        if(forwardUrl == null)
            throw new NullPointerException("forwardUrl is null. 이용할 URL을 입력하세요");
        this.forwardUrl = forwardUrl;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return forwardUrl;
    }
}
