package core.web;

public abstract class AbstractController implements Controller {
    protected ModelAndView jspView(String url) {
        return new ModelAndView(new JspView(url));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
