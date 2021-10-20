package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
    private static Sheet sheet;
    private static Workbook workbook;
    private static FileOutputStream output;
    private static FileInputStream input;
    private static String path;

    public static void openExcelFile(String fileName,String sheetName){
        path= System.getProperty("user.dir")+"/src/test/resources/testdata/"+fileName+".xlsx";
        try {
            input=new FileInputStream(path);
            workbook= new XSSFWorkbook(input);
            sheet=workbook.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            System.err.println("Excel spreadsheet path is invalid: "+path);

        } catch (IOException e) {
            System.out.println("Could not open Excel spreadsheet.");
        }
    }
   public static String getValue(int rowNum, int cellNum ){
        String value=sheet.getRow(rowNum).getCell(cellNum).toString();
        return value;
   }
   public static void setValue(int rowNum, int cellNum, String value){
        if(sheet.getPhysicalNumberOfRows()<=rowNum){
            sheet.createRow(rowNum).createCell(cellNum).setCellValue(value);
        }else if(sheet.getRow(rowNum).getPhysicalNumberOfCells()<=cellNum){
            sheet.getRow(rowNum).createCell(cellNum).setCellValue(value);
        }else{
            sheet.getRow(rowNum).getCell(cellNum).setCellValue(value);
        }
       try {
           output=new FileOutputStream(path);
           workbook.write(output);
       } catch (FileNotFoundException e) {
           System.out.println("Excel spreadsheet path is invalid "+path);
       } catch (IOException e) {
           System.out.println("Could not save changes to excel spreadsheet");
       }finally {
           try {
               output.close();
           } catch (IOException e) {
               System.out.println("Could not close FileOutputStream object");
           }
       }
   }
}
