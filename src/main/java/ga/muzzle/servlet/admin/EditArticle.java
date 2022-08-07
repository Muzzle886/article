package ga.muzzle.servlet.admin;

import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Muzzle
 */
@WebServlet("/admin/edit")
public class EditArticle extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        long categoryId = Long.parseLong(req.getParameter("categoryId"));
        String[] tagsStr = req.getParameterValues("tags[]");
        List<Long> tags = new ArrayList<>();
        for (String s : tagsStr) {
            tags.add(Long.parseLong(s));
        }
        String text = req.getParameter("text");
        ReturnMessage returnMessage;
        if (ArticleService.editArticle(id, title, text, categoryId, tags)) {
            returnMessage = new ReturnMessage(true, "文章修改成功");
        } else {
            returnMessage = new ReturnMessage(false, "发生错误");
        }
        resp.getWriter().write(returnMessage.toString());
    }
}
