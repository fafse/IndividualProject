import LanguageLevels.*;
import Recommenations.*;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndividualProject {

    public static void main(String[] args) throws IOException, SQLException {
        String dataBasePath = "/DataBase/EnglishLevels/";
        String path = "C:\\Users\\k5469\\IdeaProjects\\IndividualProject\\words\\";
        Reccomendator reccomendator = new Reccomendator(path,dataBasePath);




    }
}