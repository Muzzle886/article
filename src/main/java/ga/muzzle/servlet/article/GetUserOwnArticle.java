package ga.muzzle.servlet.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import ga.muzzle.pojo.User;
import ga.muzzle.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author Muzzle
 */
@WebServlet("/article/user")
public class GetUserOwnArticle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        resp.getWriter().write(new ObjectMapper().writeValueAsString(ArticleService.getUserArticle(user.getId())));
    }
}
