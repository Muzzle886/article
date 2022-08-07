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
@WebServlet("/user/userInfo")
public class UserInfo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("user") == null) {
            writer.write(objectMapper.writeValueAsString(""));
            return;
        }
        writer.write(objectMapper.writeValueAsString(session.getAttribute("user")));
    }
}
