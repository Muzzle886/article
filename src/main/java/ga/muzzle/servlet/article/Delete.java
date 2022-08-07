package ga.muzzle.servlet.article;

import ga.muzzle.pojo.Article;
import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.pojo.User;
import ga.muzzle.service.ArticleService;
import ga.muzzle.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Muzzle
 */
@WebServlet("/article/delete")
public class Delete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        long id = Long.parseLong(req.getParameter("id"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Article article = ArticleService.getArticle(id);
        PrintWriter writer = resp.getWriter();
        ReturnMessage returnMessage;
        if (article.getAuthor() != user.getId()) {
            returnMessage = new ReturnMessage(false, "警告！非法操作！涉嫌违规操作，您的账号已被封禁！");
            session.removeAttribute("user");
            writer.write(returnMessage.toString());
            writer.write(UserService.banUser(user.getId()) ? "用户已被封禁" : "发生错误");
        } else {
            if (ArticleService.deleteArticle(id)) {
                returnMessage = new ReturnMessage(true, "文章已删除");
            } else {
                returnMessage = new ReturnMessage(false, "发生错误");
            }
            writer.write(returnMessage.toString());
        }
    }
}

