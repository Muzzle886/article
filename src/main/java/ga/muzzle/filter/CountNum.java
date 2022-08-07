package ga.muzzle.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @author Muzzle
 */
@WebFilter({
        "/", "/index.html", "/content/*"
})
public class CountNum implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filterConfig.getServletContext().setAttribute("times", 1);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        ServletContext servletContext = req.getServletContext();
        int times = (Integer) servletContext.getAttribute("times");
        times++;
        servletContext.setAttribute("times", times);
        chain.doFilter(req, response);
    }

}
