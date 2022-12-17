package FileWorker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWorker {
    private final String pathToFile;
    public FileWorker(String pathToFile)
    {
        this.pathToFile=pathToFile;
    }

    public Boolean writeFile(List<String> words) throws IOException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(pathToFile))) {
            for (var word :
                    words) {
                out.write(word);
            }
        }catch (FileNotFoundException e)
        {
            System.out.println("Unavailable to find file...."+pathToFile);
            return false;
        }
        return true;
    }

    public List<String> readFile() throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(pathToFile))) {
            words.add(in.readLine());
        }catch (FileNotFoundException e)
        {
            throw new FileNotFoundException();
        }
        return words;
    }
}
