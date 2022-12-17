package Checkers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Checkers {
    public static class Existance{
        public static boolean directoryExists(String urlConn){
            String filepath = "jdbc:h2:"+urlConn;
            Path pathDir = Paths.get(filepath.substring(filepath.indexOf("/"), filepath.lastIndexOf("/")));
            return Files.exists(pathDir);
        }
        public static boolean tableExists(String urlConn){
            String filepath = "jdbc:h2:"+urlConn;
            Path pathTab = Paths.get(filepath.substring(filepath.indexOf("/"), filepath.length()) + ".mv.db");
            return Files.exists(pathTab);
        }
    }
}