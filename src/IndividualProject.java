package IndividualProject.src;

import Checkers.Checkers;
import Exceptions.LanguageLevelNotFoundException;
import Exceptions.LearnedWordNotFoundException;
import FileWorker.FileWorker;
import Reccomendations.Reccomendator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class IndividualProject {

    public static void printReccomendations(String text, Set<String> reccomendations,int wordPerDay) {
        int i = 1;
        System.out.println(text);
        System.out.println("===================================================");
        for (var recomendation:
                reccomendations) {
            System.out.println(i+". "+recomendation);
            System.out.print(i%wordPerDay==0?"\n\n":"");
            i++;
        }
        System.out.println("===================================================");
    }

    public static void main(String[] args) throws IOException, SQLException, LanguageLevelNotFoundException, LearnedWordNotFoundException {
        Reccomendator reccomendator = new Reccomendator();
        String name = args[0];
        String level = args[1];
        String dataBasePath = "/DataBase/EnglishLevels/";
        String path = "C:\\Users\\k5469\\IdeaProjects\\IndividualProject\\words\\";

        Set<String> reccomendations=null;

        Scanner cin = new Scanner(System.in);
        int menu=0;
        FileWorker fileWorker = new FileWorker("words\\myWords.txt");
            do {
                System.out.println("Menu");
                System.out.println("1. Get Reccomendations for a day");
                System.out.println("2. Get Reccomendations for a week");
                System.out.println("3. Get Reccomendations for a month");
                System.out.println("4. Show cur reccomendations");
                System.out.println("5. Mark word as studied");
                System.out.println("0. Exit");
                menu = Checkers.Existance.MeaningCheckers.valueChecker(">",cin,Scanner::nextInt,Scanner::hasNextInt);
                switch (menu) {
                    case 1: {
                        reccomendations= new HashSet<>(reccomendator.getRecommendations(level,3));
                        printReccomendations("Reccomendations for a Day:",reccomendations,3);
                        fileWorker.writeFile(reccomendations);
                        break;
                    }
                    case 2: {
                        reccomendations= new HashSet<>(reccomendator.getRecommendations(level,21));
                        printReccomendations("Reccomendation for a Week:",reccomendations,3);
                        fileWorker.writeFile(reccomendations);
                        break;
                    }
                    case 3: {
                        reccomendations= new HashSet<>(reccomendator.getRecommendations(level,90));
                        printReccomendations("Reccomendation for a Month:",reccomendations,3);
                        fileWorker.writeFile(reccomendations);
                        break;
                    }
                    case 4: {

                        reccomendations=new HashSet<>(fileWorker.readFile());
                        if(reccomendations.isEmpty()) {
                            System.out.println("Now reccomendations is empty...\nTry to generate them");
                        }else {
                            printReccomendations("Cur reccomendations",reccomendations,3);
                        }
                        break;
                    }
                    case 5: {
                        reccomendations=new HashSet<>(fileWorker.readFile());
                        if(reccomendations.isEmpty()) {
                            System.out.println("Now reccomendations is empty...\nTry to generate them");
                            break;
                        }else {
                            printReccomendations("Cur reccomendations",reccomendations,3);
                        }
                        int id;
                        String learnedWord="";
                        do {
                            System.out.println("Enter id of studied word");
                            id = menu = Checkers.Existance.MeaningCheckers.valueChecker(">", cin, Scanner::nextInt, Scanner::hasNextInt);
                        }while(id>reccomendations.size()||id<1);
                        Iterator it=reccomendations.iterator();
                        for(int i = 0;i<id&& it.hasNext();i++) {
                            learnedWord=it.next().toString();
                        }
                        reccomendator.addLearnedWord(learnedWord);
                        reccomendations.remove(learnedWord);
                        fileWorker.writeFile(reccomendations);
                        System.out.println("I deleted "+learnedWord);
                        break;
                    }
                    case 0: {
                        if(!reccomendations.isEmpty()) {
                            fileWorker.writeFile(reccomendations);
                        }
                        break;
                    }

                }
                cin.nextLine();
            }while (menu!=0);
    }
}