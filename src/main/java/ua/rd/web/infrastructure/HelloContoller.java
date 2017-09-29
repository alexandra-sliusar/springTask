package ua.rd.web.infrastructure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloContoller implements MyController {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try(PrintWriter writer = response.getWriter()) {
            writer.print("Hello from Servlet <br/>");
            writer.print(Thread.currentThread());
        }
    }
}
