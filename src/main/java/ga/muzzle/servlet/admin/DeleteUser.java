package ga.muzzle.servlet.admin;

import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author himea
 */
@WebServlet("/admin/remove")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        long id = Long.parseLong(req.getParameter("id"));
        ReturnMessage returnMessage;
        if (UserService.deleteUser(id)) {
            returnMessage = new ReturnMessage(true, "用户已删除");
        } else {
            returnMessage = new ReturnMessage(false, "发生错误");
        }
        resp.getWriter().write(returnMessage.toString());
    }
}
