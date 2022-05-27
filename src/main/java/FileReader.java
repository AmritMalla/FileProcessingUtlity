import java.io.File;
import java.util.List;

public interface FileReader {

    List<String> readFile(File file);
    List<String> readFile(File file, int sheetCount);
    List<String> readFile(File file, int sheetCount, int columnCount);

}