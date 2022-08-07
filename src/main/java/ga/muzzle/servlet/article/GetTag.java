package ga.muzzle.servlet.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.mapper.ArticleMapper;
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
 * @author Muzzle
 */
@WebServlet("/article/tag")
public class GetTag extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        String idStr = req.getParameter("id");
        if (idStr == null || "".equals(idStr)) {
            resp.getWriter().write("请传入正确的参数");
            return;
        } else {
            id = Long.parseLong(idStr);
        }
        resp.setContentType("application/json;charset=utf-8");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<String> tag = mapper.getTag(id);
        ObjectMapper objectMapper = new ObjectMapper();
        resp.getWriter().write(objectMapper.writeValueAsString(tag));
        sqlSession.close();
    }
}
