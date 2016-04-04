package com.courses.spalah;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author Ievgen Tararaka
 */
public class TestGenericServlet extends GenericServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        String param = servletConfig.getInitParameter("myParam");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().write("<html><body>YEAH BABY</body></html>");
    }
}
