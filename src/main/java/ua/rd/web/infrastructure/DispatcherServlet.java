package ua.rd.web.infrastructure;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    private AnnotationConfigApplicationContext webContext;

    @Override
    public void init() throws ServletException {


        AnnotationConfigApplicationContext rootContext =
                (AnnotationConfigApplicationContext) getServletContext().getAttribute("rootContext");
        String contextConfig = getInitParameter("contextConfig");
        Class<?> webContextClass = null;
        try {
            webContextClass =(Class.forName(contextConfig));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        webContext = new AnnotationConfigApplicationContext();
        webContext.setParent(rootContext);
        webContext.register(webContextClass);
        webContext.refresh();
    }

    @Override
    public void destroy() {
        webContext.close();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        MyController controller = null;
        res.setContentType("text/html");

        String beanName = beanNameByRequest(req);
        controller = (MyController) webContext.getBean(beanName);
        controller.handleRequest(req, res);
    }

    private String beanNameByRequest(HttpServletRequest req) {
        HandlerMapping handlerMapping =
                webContext.getBean(HandlerMapping.class);
        return handlerMapping.beanNameFromRequest(req);
    }
}
