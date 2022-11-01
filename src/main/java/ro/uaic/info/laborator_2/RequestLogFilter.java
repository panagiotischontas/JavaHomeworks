package ro.uaic.info.laborator_2;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class RequestLogFilter implements Filter {

    private FilterConfig config = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if ( this.config == null ) {
            return;
        }

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println("test123");
        printWriter.flush();

        this.config.getServletContext().log(stringWriter.getBuffer().toString());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
