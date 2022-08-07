package ga.muzzle.servlet.admin;

import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author himear
 */
@WebServlet("/admin/article/delete")
public class DeleteArticle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        ReturnMessage returnMessage;
        if (ArticleService.deleteArticle(id)) {
            returnMessage = new ReturnMessage(true, "文章已删除");
        } else {
            returnMessage = new ReturnMessage(false, "发生错误");
        }
        resp.getWriter().write(returnMessage.toString());
    }
}
