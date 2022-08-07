package ga.muzzle.servlet.admin;

import ga.muzzle.mapper.FileMapper;
import ga.muzzle.pojo.ReturnMessage;
import ga.muzzle.utils.ExcelUtils;
import ga.muzzle.utils.MybatisUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/import/user")
@MultipartConfig
public class ImportUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String filepath = req.getServletContext().getRealPath("/WEB-INF/upload/");
        File file = new File(filepath);
        Part part = req.getPart("file");
        String filename = part.getSubmittedFileName();
        if (file.exists() || file.mkdir()) {
            part.write(filepath + filename);
        }
        List<Map<String, Object>> excelData = ExcelUtils.getExcelData(new File(filepath + filename));
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FileMapper mapper = sqlSession.getMapper(FileMapper.class);
        for (Map<String, Object> excelDatum : excelData) {
            mapper.importUser(excelDatum);
        }
        ReturnMessage returnMessage = new ReturnMessage(true, "导入成功！");
        resp.getWriter().write(returnMessage.toString());
        sqlSession.commit();
        sqlSession.close();
    }
}
