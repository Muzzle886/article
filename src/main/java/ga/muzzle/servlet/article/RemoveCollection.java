package ga.muzzle.servlet.article;

import ga.muzzle.mapper.ArticleMapper;
import ga.muzzle.pojo.Favorite;
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

@WebServlet("/article/collection/remove")
public class RemoveCollection extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long articleId = Long.parseLong(req.getParameter("id"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        long userId = user.getId();
        Favorite favorite = new Favorite(articleId, userId);
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        mapper.removeCollection(favorite);
        sqlSession.commit();
        sqlSession.close();
    }
}
