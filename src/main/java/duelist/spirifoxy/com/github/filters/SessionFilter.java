package duelist.spirifoxy.com.github.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SessionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        List<String> allowed = Arrays.asList("login", "favicon.ico", "css", "js");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();

        if (session.getAttribute("user") != null ||
                Objects.equals(req.getRequestURI(), "/") ||
                allowed.contains(req.getRequestURI().split("/")[1])) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}
