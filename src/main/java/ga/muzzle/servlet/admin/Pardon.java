package ga.muzzle.servlet.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.mapper.UserMapper;
import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.utils.MybatisUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

@WebServlet("/admin/pardon")
public class Pardon extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.pardonUser(id);
        resp.getWriter().write(new ObjectMapper().writeValueAsString(new ReturnMessage(true,"成功")));
        sqlSession.commit();
        sqlSession.close();
    }
}
