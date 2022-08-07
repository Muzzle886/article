package ga.muzzle.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author himear
 */
@WebFilter(urlPatterns = {
        "/admin/*", "/static/*"
})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session.getAttribute("admin") == null) {
            resp.sendRedirect("/adminLogin.jsp");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
