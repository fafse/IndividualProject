import Exceptions.LanguageLevelNotFoundException;
import FileWorker.FileWorker;
import LanguageLevels.*;
import Recommenations.*;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IndividualProject {

    public static void main(String[] args) throws IOException, SQLException, LanguageLevelNotFoundException {
        String dataBasePath = "/DataBase/EnglishLevels/";
        String path = "C:\\Users\\k5469\\IdeaProjects\\IndividualProject\\words\\";
        Reccomendator reccomendator = new Reccomendator();
        Set<String> reccomendations = new HashSet<>(reccomendator.getReccomendations("C1"));
        System.out.println(reccomendations);
        FileWorker fileWorker = new FileWorker();
        fileWorker.writeFile(reccomendations);


    }
}