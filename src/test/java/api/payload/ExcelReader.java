package api.payload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelReader {

    // Method to read Excel file column and return values as an array
    public static String[] readExcelColumn(String filePath, String sheetName, int columnIndex) {
        String[] columnData = null;
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetName);

            // Calculate number of rows in the sheet
            int rows = sheet.getPhysicalNumberOfRows();

            // Initialize array to store column data
            columnData = new String[rows - 1]; // Subtract 1 for excluding header row

            for (int i = 1; i < rows; i++) { // Start from 1 to skip header row
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(columnIndex);
                String cellValue = cell.getStringCellValue();
                columnData[i - 1] = cellValue; // Store cell value in array
            }

            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnData;
    }

   

  
}