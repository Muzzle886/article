package ga.muzzle.servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.pojo.ReturnMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author himear
 */
@WebServlet("/user/signOut")
public class SignOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        session.removeAttribute("user");

        PrintWriter writer = resp.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(new ReturnMessage(true,"登出成功")));
        writer.write("跳转回主页！");
        resp.sendRedirect("/index.jsp");
    }
}
