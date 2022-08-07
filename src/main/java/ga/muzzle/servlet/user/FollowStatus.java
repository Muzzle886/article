package ga.muzzle.servlet.user;

import ga.muzzle.pojo.User;
import ga.muzzle.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Muzzle
 */
@WebServlet("/user/follow/status")
public class FollowStatus extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        long id = Long.parseLong(req.getParameter("id"));
        long user = ((User) req.getSession().getAttribute("user")).getId();
        boolean status = UserService.getFollowStatus(id, user);
        resp.getWriter().write(String.valueOf(status));
    }
}
