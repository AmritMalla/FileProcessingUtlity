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

public class ExcelFileWriter {
    
    class Node{
        String key;
        long value;

        public Node(String key, long value) {
            this.key = key;
            this.value = value;
        }
    }

    public void writeToExcelFile(List<String>  list, String fileName){
        try{
            
        
        PriorityQueue<Node> priorityQueue = processList(list);
        
        //Create workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        //Create Sheet 
        XSSFSheet spreadSheet = workbook.createSheet("Result");
        
        int rowNumber = 0;
        //Header Row
        XSSFRow row = spreadSheet.createRow(rowNumber++);

        int cellNumber = 0;
        XSSFCell cell = row.createCell(cellNumber++);
        cell.setCellValue("Numbers");

        cell = row.createCell(cellNumber++);
        cell.setCellValue("Place");

        cell = row.createCell(cellNumber++);
        cell.setCellValue("Occurrences");
        
        
        long lastValue = -1;
        long place = 0;
        
        while (priorityQueue.size() > 0){
            
            Node node = priorityQueue.poll();
            cellNumber = 0;
            
            row = spreadSheet.createRow(rowNumber++);
            
            cell = row.createCell(cellNumber++);
            cell.setCellValue(node.key);
            
            cell = row.createCell(cellNumber++);
            if(node.value == lastValue){
                cell.setCellValue(place);
            }else{
                cell.setCellValue(++place);
            }

            lastValue = node.value;
            
            cell = row.createCell(cellNumber++);
            cell.setCellValue(node.value);
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
    
    public PriorityQueue<Node> processList(List<String> list){
        PriorityQueue<Node> priorityQueue =  new PriorityQueue<>(Comparator.comparing(a->-a.value));
        Map<String, Long> map = list.stream()
                .map(String::trim)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
       
        map.forEach((k,v)-> priorityQueue.add(new Node(k,v)));
        return priorityQueue;
    }
}
