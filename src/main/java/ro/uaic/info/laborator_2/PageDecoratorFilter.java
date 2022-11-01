package ro.uaic.info.laborator_2;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class PageDecoratorFilter implements Filter {

    private FilterConfig config = null;
    private String header = "";
    private String footer = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        this.config = filterConfig;
        if ( this.config != null ) {
            String tHeader = this.config.getInitParameter("preludeText");
            String tFooter = this.config.getInitParameter("codaText");

            if ( tHeader != null ) {
                this.header = tHeader;
            }

            if ( tFooter != null ) {
                this.footer = tFooter;
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if ( this.config == null ) {
            return;
        }

        PrintWriter out = servletResponse.getWriter();
        out.print("<header>" + this.header + "</header>");
        filterChain.doFilter(servletRequest, servletResponse);
        out.print("<header>" + this.footer + "</header>");
        out.close();
    }
}
