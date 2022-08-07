package ga.muzzle.servlet.user;

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
@WebServlet("/user/follow/count")
public class FollowCount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        long id = Long.parseLong(req.getParameter("id"));
        long count = UserService.getFollowCount(id);
        resp.getWriter().write(String.valueOf(count));
    }
}
