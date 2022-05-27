import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExcelFileReader implements FileReader {
    
    private static final String XLSX_EXTENSION = "xlsx";

    private static final String XLS_EXTENSION = "xls";

    private static final String ROOT = "files";

    public List<String> readFile(File file) {
        return readFile(file, 1);
    }

    public List<String> readFile(File file, int sheetCount) {
        return readFile(file, sheetCount, 1);
    }


    public List<String> readFile(File file, int sheetCount, int columnCount) {
        String filename = file.getName();
        String extension = FileNameUtils.getExtension(file.getName());

        if (!extension.equals(XLS_EXTENSION) && !extension.equals(XLSX_EXTENSION)) {
            throw new RuntimeException("File Not supported");
        }
        Set<String> words = new HashSet<>();

        try {
            FileInputStream inputStream = new FileInputStream(new File(ROOT + File.separator + filename));
            Workbook workbook = new XSSFWorkbook(inputStream);
            for (int i = 0; i < sheetCount; i++) {
                Sheet sheet = workbook.getSheetAt(i);

                int rowNumber = 0;
                Row row = sheet.getRow(rowNumber);
                while (row != null) {
                    for (int column = 0; column < columnCount; column++) {
                        String stringCellValue = row.getCell(column).getStringCellValue();

                        if (stringCellValue.length() > 0) {
                            words.add(stringCellValue.toLowerCase().trim());
                        }else{
                            System.out.println("Invalid words at : " +  row.getCell(column).getAddress().formatAsString());
                        }
                    }
                    rowNumber++;
                    row = sheet.getRow(rowNumber);
                }
            }
            System.out.println("Words :: " + words);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(words);
    }
}
