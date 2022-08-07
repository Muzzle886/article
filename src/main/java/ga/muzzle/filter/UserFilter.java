package ga.muzzle.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author Muzzle
 */
@WebFilter({"/article/create", "/article/delete", "/user/follow/status", "/article/collection/status","/user/follow"})
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.getWriter().write(String.valueOf(false));
        } else {
            chain.doFilter(req, resp);
        }
    }
}
