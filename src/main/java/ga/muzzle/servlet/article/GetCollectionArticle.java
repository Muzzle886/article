package ga.muzzle.servlet.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.mapper.ArticleMapper;
import ga.muzzle.pojo.Article;
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
import java.util.List;

/**
 * @author Muzzle
 */
@WebServlet("/article/collection/articles")
public class GetCollectionArticle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
        List<Article> collectionArticle = articleMapper.getCollectionArticle(user.getId());
        resp.getWriter().write(new ObjectMapper().writeValueAsString(collectionArticle));
        sqlSession.close();
    }
}
