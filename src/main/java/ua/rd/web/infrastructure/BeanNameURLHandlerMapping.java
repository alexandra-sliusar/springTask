package ua.rd.web.infrastructure;

import javax.servlet.http.HttpServletRequest;

public class BeanNameURLHandlerMapping implements HandlerMapping {
    @Override
    public String beanNameFromRequest(HttpServletRequest request) {
        String requestURL = request.getRequestURI();
        String beanName =
                requestURL.substring(requestURL.lastIndexOf('/') + 1);
        return beanName;
    }
}
