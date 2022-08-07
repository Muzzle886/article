package ga.muzzle.servlet.article;

import ga.muzzle.mapper.ArticleMapper;
import ga.muzzle.pojo.Favorite;
import ga.muzzle.utils.MybatisUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * @author himear
 */
@WebServlet("/article/count")
public class GetCollectionCount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        long id = Long.parseLong(req.getParameter("id"));
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Favorite> collectionList = mapper.getCollectionCount(id);
        int count = collectionList.size();
        resp.getWriter().write(String.valueOf(count));
    }
}
