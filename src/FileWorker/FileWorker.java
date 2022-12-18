package FileWorker;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileWorker {
    private String pathToFile="words\\myWords.txt";

    public Boolean writeFile(Set<String> words) throws IOException {
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

    public Set<String> readFile() throws IOException {
        Set<String> words = new HashSet<>();
        try (BufferedReader in = new BufferedReader(new FileReader(pathToFile))) {
            words.add(in.readLine());
        }catch (FileNotFoundException e)
        {
            throw new FileNotFoundException();
        }
        return words;
    }
}
