import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExcelFileWriterForComparision {
    

    public void writeToExcelFile(List<String>  list, String fileName){
        try{
            
        //Create workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        //Create Sheet 
        XSSFSheet spreadSheet = workbook.createSheet("Result");
        
        int rowNumber = 0;
        
        for(String s: list){
        XSSFRow row = spreadSheet.createRow(rowNumber++);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(s);
        }
        
        FileOutputStream out = new FileOutputStream(
                new File(fileName + ".xlsx"));

        workbook.write(out);
        out.close();
        }catch (IOException e){
            System.out.println("Couldn't process the file");
            e.printStackTrace();
        }

        System.out.println("Successfully generated result with name " + fileName + ".xlsx");
        
    }
    
}
