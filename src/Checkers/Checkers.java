package Checkers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.function.Function;

public class Checkers {
    public static class Existance{
        public static class MeaningCheckers{
            public static <T> T valueChecker(String msg, Scanner scanner, Function<Scanner, T> readfunc, Function<Scanner, Boolean> checkFunc){
                do{
                    System.out.print(msg + ": ");
                    if(checkFunc.apply(scanner))
                        return readfunc.apply(scanner);
                    else {
                        System.out.println("Введено неверное значение.");
                        scanner.nextLine();
                    }
                }while (true);
            }
        }
        public static boolean tableExists(String urlConn){
            String filepath = "jdbc:h2:"+urlConn;
            Path pathTab = Paths.get(filepath.substring(filepath.indexOf("\\"), filepath.length()) + ".mv.db");
            return Files.exists(pathTab);
        }
    }
}