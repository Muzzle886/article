package ga.muzzle.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    public static List<Map<String, Object>> getExcelData(File file) throws IOException {
        String fileName = file.getName();
        String substring = fileName.substring(fileName.lastIndexOf('.'));
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook;
        if (".xlsx".equals(substring)) {
            workbook = new XSSFWorkbook(fileInputStream);
        } else {
            workbook = new HSSFWorkbook(fileInputStream);
        }
        Sheet sheet = workbook.getSheetAt(0);
        Row header = sheet.getRow(0);
        String[] headers = new String[header.getLastCellNum() + 1];
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < header.getLastCellNum(); i++) {
            headers[i] = header.getCell(i).getStringCellValue();
        }
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i + 1);
            Map<String, Object> map = new HashMap<>(sheet.getLastRowNum());
            for (int j = 0; j < row.getLastCellNum(); j++) {
                map.put(headers[j], row.getCell(j).getStringCellValue());
            }
            list.add(map);
        }
        fileInputStream.close();
        return list;
    }
}
