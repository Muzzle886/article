package ga.muzzle.servlet.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.pojo.Article;
import ga.muzzle.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Muzzle
 */
@WebServlet("/article/id")
public class GetArticleById extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        long id = Long.parseLong(req.getParameter("id"));
        Article article = ArticleService.getArticle(id);
        resp.getWriter().write(new ObjectMapper().writeValueAsString(article));
    }
}
