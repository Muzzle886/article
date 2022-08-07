package ga.muzzle.servlet.admin;

import ga.muzzle.mapper.ArticleMapper;
import ga.muzzle.mapper.FileMapper;
import ga.muzzle.pojo.Article;
import ga.muzzle.utils.MybatisUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author himea
 */
@WebServlet("/admin/export/article")
public class ExportArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        //设置文件响应头
        resp.setHeader("Content-Disposition", "attachment;fileName=" + "article-" + new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()) + ".xlsx");
        //获取响应输出流
        ServletOutputStream outputStream = resp.getOutputStream();

        //获取sqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        //查询语句
        List<Article> articles = mapper.getAllArticle();
        //新建Excel文件
        Workbook workbook = new XSSFWorkbook();
        //新建工作表
        Sheet sheet = workbook.createSheet();
        //设置头
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("article_id");
        header.createCell(1).setCellValue("article_title");
        header.createCell(2).setCellValue("article_author");
        header.createCell(3).setCellValue("author_name");
        header.createCell(4).setCellValue("article_date");
        header.createCell(5).setCellValue("article_category");
        header.createCell(6).setCellValue("category_name");
        header.createCell(7).setCellValue("article_text");
        //写入数据
        for (int i = 0; i < articles.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Article article = articles.get(i);
            row.createCell(0).setCellValue(article.getId());
            row.createCell(1).setCellValue(article.getTitle());
            row.createCell(2).setCellValue(article.getAuthor());
            row.createCell(3).setCellValue(article.getAuthorName());
            row.createCell(4).setCellValue(article.getDate());
            row.createCell(5).setCellValue(article.getCategoryId());
            row.createCell(6).setCellValue(article.getCategory());
            row.createCell(7).setCellValue(article.getText());
        }
        //文件输出
        workbook.write(outputStream);
        //关闭流
        sqlSession.close();
        outputStream.close();
    }
}
