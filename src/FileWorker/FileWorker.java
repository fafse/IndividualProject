package FileWorker;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileWorker {
    private String pathToFile;

    public FileWorker(String pathToFile)
    {
        this.pathToFile=pathToFile;
    }

    public Boolean writeFile(Set<String> words) throws IOException {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(pathToFile))) {
            for (var word :
                    words) {
                out.write(word+"\n");
            }
        }catch (FileNotFoundException e)
        {
            System.out.println("Unavailable to find file...."+pathToFile);
            return false;
        }
        return true;
    }

    public Set<String> readFile() throws IOException {
        Set<String> words = new HashSet<>();

        try (BufferedReader in = new BufferedReader(new FileReader(pathToFile))) {
            String word = in.readLine();
            while(word!=null) {
                words.add(word);
                word = in.readLine();
            }
        }catch (FileNotFoundException e)
        {
            throw new FileNotFoundException();
        }
        return words;
    }
}
