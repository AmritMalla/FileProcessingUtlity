import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvFileReader implements FileReader {
    
    @Override
    public List<String> readFile(File file) {
        List<String> wordList = new ArrayList<>();

        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                wordList.addAll(Arrays.asList(words));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    @Override
    public List<String> readFile(File file, int sheetCount) {
        return readFile(file);
    }

    @Override
    public List<String> readFile(File file, int sheetCount, int columnCount) {
        return readFile(file);
    }
}
