package ga.muzzle.servlet.admin;

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
@WebServlet("/admin/tag/id")
public class GetTagId extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Long> tagId = mapper.getTagId(id);
        ObjectMapper objectMapper = new ObjectMapper();
        resp.getWriter().write(objectMapper.writeValueAsString(tagId));
    }
}
