package com.qa.utilities;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtils {

    private static FileInputStream ExcelFile;
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;

    //This method is to set the File path and to open the Excel file
    //Pass Excel Path and SheetName as Arguments to this method
    public static void setExcelFile(String Path,String SheetName) throws Exception {
        ExcelFile = new FileInputStream(Path);
        ExcelWBook = new XSSFWorkbook(ExcelFile);
        ExcelWSheet = ExcelWBook.getSheet(SheetName);
    }

    //This method is to read the test data from the Excel cell
    //In this we are passing parameters/arguments as Row Num and Col Num
    public static String getCellData(int RowNum, int ColNum) throws Exception{
        Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
        String CellData = Cell.getStringCellValue();
        return CellData;
    }

    public static int getRowCount(String Path,String SheetName) throws Exception {
        setExcelFile(Path,SheetName);
        int rowcount = ExcelWSheet.getLastRowNum();
        ExcelWBook.close();
        ExcelFile.close();
        return rowcount;
    }

    public static int getColumnCount(String Path,String SheetName) throws Exception {
        setExcelFile(Path,SheetName);
        int colcount = ExcelWSheet.getRow(1).getLastCellNum();
        ExcelWBook.close();
        ExcelFile.close();
        return colcount;
    }
}
