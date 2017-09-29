package ua.rd.web.infrastructure;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Oleksandra_Sliusar on 29-Sep-17.
 */
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String contextConfig = servletContextEvent.getServletContext().getInitParameter("contextConfig");
        String[] contextConfigs = contextConfig.split(" ");
        AnnotationConfigApplicationContext rootContext = new AnnotationConfigApplicationContext(contextConfigs);
        servletContextEvent.getServletContext().setAttribute("rootContext",rootContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
