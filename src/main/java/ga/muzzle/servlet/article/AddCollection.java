package ga.muzzle.servlet.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.mapper.ArticleMapper;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @author himea
 */
@WebServlet("/article/collection/add")
public class AddCollection extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        String articleId = req.getParameter("id");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>(2);
        map.put("article_id", articleId);
        map.put("user_id", user.getId());
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        if (mapper.collectionArticle(map)>0){
            resp.getWriter().write(new ObjectMapper().writeValueAsString(new ReturnMessage(true,"收藏成功")));
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
