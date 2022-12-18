import Exceptions.LanguageLevelNotFoundException;
import Exceptions.LearnedWordNotFoundException;
import FileWorker.FileWorker;
import LearnedWords.*;
import Recommenations.*;

import java.io.*;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class IndividualProject {

    public static void main(String[] args) throws IOException, SQLException, LanguageLevelNotFoundException, LearnedWordNotFoundException {
        String dataBasePath = "/DataBase/EnglishLevels/";
        String path = "C:\\Users\\k5469\\IdeaProjects\\IndividualProject\\words\\";
        Reccomendator reccomendator = new Reccomendator();
        Set<String> reccomendations = new HashSet<>(reccomendator.getRecommendations("A2"));
        System.out.println(reccomendations);
        FileWorker fileWorker = new FileWorker("words\\myWords.txt");
        fileWorker.writeFile(reccomendations);



        System.out.println("Menu");
        System.out.println("1. Get Reccomendations for a day");

    }
}