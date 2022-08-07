package ga.muzzle.servlet.admin;

import ga.muzzle.mapper.FileMapper;
import ga.muzzle.pojo.Article;
import ga.muzzle.pojo.User;
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
@WebServlet("/admin/export/user")
public class ExportUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        //设置文件响应头
        resp.setHeader("Content-Disposition", "attachment;fileName=" + "user-" + new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()) + ".xlsx");
        //获取响应输出流
        ServletOutputStream outputStream = resp.getOutputStream();

        //获取sqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FileMapper mapper = sqlSession.getMapper(FileMapper.class);
        //查询语句
        List<User> users = mapper.exportUser();
        //新建Excel文件
        Workbook workbook = new XSSFWorkbook();
        //新建工作表
        Sheet sheet = workbook.createSheet();
        //设置头
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("user_id");
        header.createCell(1).setCellValue("user_username");
        header.createCell(2).setCellValue("user_name");
        header.createCell(3).setCellValue("user_tel");
        header.createCell(4).setCellValue("user_email");
        //写入数据
        for (int i = 0; i < users.size(); i++) {
            Row row = sheet.createRow(i + 1);
            User user = users.get(i);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getUsername());
            row.createCell(2).setCellValue(user.getName());
            row.createCell(3).setCellValue(user.getTel());
            row.createCell(4).setCellValue(user.getEmail());
        }
        //文件输出
        workbook.write(outputStream);
        //关闭流
        sqlSession.close();
        outputStream.close();
    }
}
