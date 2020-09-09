package core.mvc;

/**
 * Created by jyami on 2020/09/09
 */
public abstract class AbstractController implements Controller{
    protected ModelAndView jspView(String forwardUrl){
        return new ModelAndView(new JspView(forwardUrl));
    }

    protected ModelAndView jsonView(){
        return new ModelAndView(new JsonView());
    }

}
