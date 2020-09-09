package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jyami on 2020/08/27
 */
public class ForwardController extends AbstractController {

    private String forwardUrl;

    public ForwardController(String forwardUrl) {
        if(forwardUrl == null)
            throw new NullPointerException("forwardUrl is null. 이용할 URL을 입력하세요");
        this.forwardUrl = forwardUrl;
    }

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        return jspView(forwardUrl);
    }
}
