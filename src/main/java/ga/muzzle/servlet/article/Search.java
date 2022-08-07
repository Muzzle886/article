package ga.muzzle.servlet.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.pojo.Article;
import ga.muzzle.service.SearchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author Muzzle
 */
@WebServlet("/article/search")
public class Search extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        String method = req.getParameter("method");
        String keyword = req.getParameter("keyword");
        List<Article> search = SearchService.search(method, keyword);
        ObjectMapper objectMapper = new ObjectMapper();
        resp.getWriter().write(objectMapper.writeValueAsString(search));
    }
}
