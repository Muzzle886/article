package ga.muzzle.servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.mapper.UserMapper;
import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.pojo.User;
import ga.muzzle.utils.MybatisUtils;
import ga.muzzle.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * @author himear
 */
@WebServlet("/user/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        ReturnMessage returnMessage = new ReturnMessage(false, "未知错误");
        HttpSession session = req.getSession();
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean rememberMe;
        String s = req.getParameter("rememberMe");
        if (s == null) {
            rememberMe = false;
        } else {
            rememberMe = Boolean.parseBoolean(s);
        }
        //查询用户
        User user = mapper.getUserByUserName(username);
        if (null == user) {
            returnMessage.setMessage("用户不存在");
        } else if (user.getStatus() == 1) {
            returnMessage.setMessage("账号已被封禁，如有异议，请联系管理员。");
        } else if (password.equals(user.getPassword())) {
            returnMessage.setSuccess(true);
            returnMessage.setMessage("登陆成功");
            session.setAttribute("user", user);
            Cookie serviceId = new Cookie("serviceId", StringUtils.getRandomString(32));
            serviceId.setMaxAge(60 * 60 * 24);
            if (rememberMe) {
                Cookie username1 = new Cookie("username", username);
                username1.setMaxAge(60 * 60 * 24 * 7);
                username1.setPath("/*");
                resp.addCookie(username1);
                Cookie password1 = new Cookie("password", password);
                password1.setMaxAge(60 * 60 * 24 * 7);
                password1.setPath("/*");
                resp.addCookie(password1);
            }
            resp.addCookie(serviceId);
        } else {
            returnMessage.setMessage("密码错误");
        }
        resp.getWriter().write(new ObjectMapper().writeValueAsString(returnMessage));
        sqlSession.close();
    }
}
