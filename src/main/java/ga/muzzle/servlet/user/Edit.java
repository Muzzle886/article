package ga.muzzle.servlet.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.mapper.UserMapper;
import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.pojo.User;
import ga.muzzle.utils.MybatisUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

@WebServlet("/user/edit")
public class Edit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        ReturnMessage returnMessage = new ReturnMessage(false, "未知错误");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        HttpSession session = req.getSession();
        User user1 = (User) session.getAttribute("user");
        long id = user1.getId();
        long tel = Long.parseLong(req.getParameter("tel"));
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        User user = new User(id, tel, email, name);
        session.setAttribute("user", user);
        mapper.editUser(user);
        returnMessage.setSuccess(true);
        returnMessage.setMessage("修改成功");
        resp.getWriter().write(returnMessage.toString());
        sqlSession.commit();
        sqlSession.close();
    }
}
