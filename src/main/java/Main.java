import org.apache.commons.compress.utils.FileNameUtils;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        if (args.length == 0) {
            System.out.println("Couldn't find the input file name");
            System.out.println("Please enter the file name");
            return;
        }
        
        String option = args[0].toLowerCase();
        
        switch (option){
            case "compare":{
                
                String extension = FileNameUtils.getExtension(args[1]);
                
                FileReader fileReader = getFileReader(extension);
                if (fileReader == null) return;

                File file = new File(args[1]);
                List<String> list1 = fileReader.readFile(file);
                
                // Process second file
                String extension2 = FileNameUtils.getExtension(args[2]);

                fileReader = getFileReader(extension2);
                if (fileReader == null) return;
                
                File file2 = new File(args[2]);
                List<String> list2 = fileReader.readFile(file2);

                ExcelFileWriterForComparision excelFileWriter = new ExcelFileWriterForComparision();
                excelFileWriter.writeToExcelFile(Utility.getDiff(list1, list2), "AminusB");
                excelFileWriter.writeToExcelFile(Utility.getDiff(list2, list1), "BminusA");
                
                
                break;
            }
            default:{
                String extension = FileNameUtils.getExtension(args[0]);
                
                FileReader fileReader = getFileReader(extension);
                if (fileReader == null) return;

                File file = new File(args[0]);
                List<String> list = fileReader.readFile(file);
                ExcelFileWriter excelFileWriter = new ExcelFileWriter();
                excelFileWriter.writeToExcelFile(list,"Output");
            }
            
        }
        
       
    }

    private static FileReader getFileReader(String extension2) {
        FileReader fileReader;
        if(extension2.equalsIgnoreCase("txt")){
            fileReader = new TextFileReader();
        }else if(extension2.equalsIgnoreCase("xlsx")||extension2.equalsIgnoreCase("xls")){
            fileReader = new ExcelFileReader();
        }else if(extension2.equalsIgnoreCase("csv")){
            fileReader = new CsvFileReader();
        }else{
            System.out.println("Invalid file name extension");
            System.out.println("Please put file with txt, xlsx, xls, or csv extension");
            return null;
        }
        return fileReader;
    }


}
