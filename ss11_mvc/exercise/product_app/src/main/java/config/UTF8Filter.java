package config;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "UTF8Filter", value = "/*")
public class UTF8Filter implements Filter {
    public void init(FilterConfig config) throws ServletException {

        //<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
