package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTest {
   // public static void main(String[] args) {
     //   String path="/Users/galinaluca/IdeaProjects/MindtekCucumberAutomation/src/test/resources/testdata/Test Data.xlsx";
     //   try {
      //      FileInputStream input=new FileInputStream(path);
       //     Workbook workbook= new XSSFWorkbook(input);
        //    Sheet sheet1=workbook.getSheet("Sheet1");
        //    String firstName=sheet1.getRow(1).getCell(0).toString();

        //    System.out.println(firstName);
        //    System.out.println(sheet1.getRow(2).getCell(1));
         //   sheet1.getRow(2).getCell(1).setCellValue("Lakka");
         //   sheet1.createRow(3).createCell(0).setCellValue("Kim");
          //  int numberOfColumnsRow2=sheet1.getRow(1).getPhysicalNumberOfCells();
          //  System.out.println(numberOfColumnsRow2);
          //  for(int i=0; i<numberOfColumnsRow2; i++){
           //     System.out.println(sheet1.getRow(1).getCell(i));
        //    }
         //   System.out.println(sheet1.getPhysicalNumberOfRows());
         //   FileOutputStream output=new FileOutputStream(path);
          //  workbook.write(output);

      //  } catch (IOException e) {
      //      e.printStackTrace();
      //  }
    //}
      public static void main(String[] args) {
          ExcelUtils.openExcelFile("Test Data","Sheet1");
          System.out.println(ExcelUtils.getValue(1,1));

          ExcelUtils.setValue(1,1,"Clark");
          ExcelUtils.setValue(4,0,"Sumathi");
          ExcelUtils.setValue(4,1,"Indira");
      }
}
