package ga.muzzle.servlet.user;

import ga.muzzle.pojo.ReturnMessage;
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
@WebServlet("/user/follow")
public class Follow extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        long followerId = Long.parseLong(req.getParameter("id"));
        long followersId = ((User) req.getSession().getAttribute("user")).getId();
        ReturnMessage returnMessage;
        if (UserService.follow(followerId, followersId)) {
            returnMessage = new ReturnMessage(true, "关注成功");
        } else {
            returnMessage = new ReturnMessage(false, "发生错误");
        }
        resp.getWriter().write(returnMessage.toString());
    }
}
