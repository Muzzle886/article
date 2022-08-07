package ga.muzzle.servlet.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Muzzle
 */
@WebServlet("/user/admin/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (AdminService.adminLogin(username, password)) {
            req.getSession().setAttribute("admin", username);
            resp.sendRedirect("/static");
        }else {
            resp.sendRedirect("/adminLogin.jsp");
        }
    }
}
