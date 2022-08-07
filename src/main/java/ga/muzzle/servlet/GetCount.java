package ga.muzzle.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.mapper.ArticleMapper;
import ga.muzzle.mapper.UserMapper;
import ga.muzzle.pojo.Article;
import ga.muzzle.pojo.User;
import ga.muzzle.utils.MybatisUtils;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Muzzle
 */
@WebServlet("/counts")
public class GetCount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        ServletContext servletContext = req.getServletContext();
        int times = (Integer) servletContext.getAttribute("times");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Article> allArticle = articleMapper.getAllArticle();
        List<User> user = userMapper.getUser();
        sqlSession.close();
        Map<String, Integer> map = new HashMap<>(3);
        map.put("times", times);
        map.put("article", allArticle.size());
        map.put("user", user.size());
        resp.getWriter().write(new ObjectMapper().writeValueAsString(map));
    }
}
