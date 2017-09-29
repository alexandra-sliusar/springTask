package ua.rd.web.infrastructure;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {

    String beanNameFromRequest(HttpServletRequest request);

}
