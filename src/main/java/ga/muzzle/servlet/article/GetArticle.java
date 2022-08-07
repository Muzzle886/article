package ga.muzzle.servlet.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.mapper.ArticleMapper;
import ga.muzzle.pojo.Article;
import ga.muzzle.service.ArticleService;
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
@WebServlet("/article/getArticle")
public class GetArticle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new ObjectMapper().writeValueAsString(ArticleService.getAllArticle()));
    }
}
