package ga.muzzle.servlet.article;

import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.pojo.User;
import ga.muzzle.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author himea
 */
@WebServlet("/article/create")
public class Create extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String title = req.getParameter("title");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        long id = user.getId();
        long category = Long.parseLong(req.getParameter("category"));
        String text = req.getParameter("text");
        String[] tags = req.getParameterValues("tags[]");
        List<Long> lists = new ArrayList<>();
        for (String tag : tags) {
            lists.add(Long.parseLong(tag));
        }
        ReturnMessage returnMessage;
        if (ArticleService.createArticle(title, id, category, text, lists)) {
            returnMessage = new ReturnMessage(true, "发布成功");
        } else {
            returnMessage = new ReturnMessage(false, "发生错误，请稍后再试");
        }
        resp.getWriter().write(returnMessage.toString());
    }
}
