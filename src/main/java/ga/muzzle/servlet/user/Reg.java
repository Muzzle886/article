package ga.muzzle.servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.mapper.UserMapper;
import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.pojo.User;
import ga.muzzle.service.UserService;
import ga.muzzle.utils.MybatisUtils;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * @author himear
 */
@WebServlet("/user/reg")
public class Reg extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String telStr = req.getParameter("tel");
        long tel;
        if (telStr == null || "".equals(telStr)) {
            tel = 0;
        } else {
            tel = Long.parseLong(telStr);
        }
        String email = req.getParameter("email");
        if (email == null || "".equals(email)) {
            email = "";
        }
        String name = req.getParameter("name");
        User user = new User(tel, email, password, username, name);
        resp.getWriter().write(UserService.createUser(user).toString());
    }
}
