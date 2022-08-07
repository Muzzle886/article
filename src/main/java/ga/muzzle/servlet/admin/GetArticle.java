package ga.muzzle.servlet.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author himea
 */
@WebServlet("/admin/getArticle")
public class GetArticle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(new ObjectMapper().writeValueAsString(ArticleService.getAllArticle()));
    }
}
