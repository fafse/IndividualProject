package LearnedWords;

import Checkers.Checkers;
import Exceptions.LanguageLevelNotFoundException;
import Exceptions.LearnedWordNotFoundException;
import LanguageLevels.LanguageLevel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class LearnedWordsDao {


    static LearnedWords learnedWords;

    static {
        try {
            learnedWords = new LearnedWords();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LearnedWordsDao() throws SQLException {
    }

    private void update(Statement statement, int first, String second, String third) throws SQLException {
        statement.execute("INSERT INTO learnedWords VALUES" +
                "(" + first + ", " + second + ", "+third+")");
    }

    public static String getWord() throws SQLException, LearnedWordNotFoundException {
        int randomId = (int) ((Math.random() * ((learnedWords.getNumWords() - 1) + 1)) + 1);
        Statement statement = learnedWords.getStatement();
        if(statement==null) {
            throw new LearnedWordNotFoundException("Unavailable to find learnedWords");
        }
        try (ResultSet result = statement.executeQuery("SELECT * FROM learnedWords " +
                "WHERE ID = " + "'"+randomId+"'")) {
            if (result.next()) {
                return result.getString("WORD") + "\t" + result.getString("TRANSLATION");
            } else {
                return null;
            }
        }
    }

    public void addLearnedWord(int id,String word) throws SQLException {
        String[] learnedword= word.split("\t");
        if(!isAdded(learnedword[0])) {
            update(learnedWords.getStatement(), id, "'"+learnedword[0]+"'","'"+ learnedword[1]+"'");
        }
    }

    public boolean isAdded(String word) throws SQLException {
        Connection connection = learnedWords.getConn();
        try (Statement statement = connection.createStatement()){
            try (ResultSet result = statement.executeQuery("SELECT * FROM learnedWords " +
                    "WHERE word = " + "'"+word+"'")) {
                if (result.next()) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }


    public void fillTable(Set<String> words) throws IOException, SQLException, LearnedWordNotFoundException {
                Statement statement = learnedWords.getStatement();
                if (statement == null) {
                    throw new LearnedWordNotFoundException("Unavailable to find " + learnedWords);
                }
                int id = 1;
                statement.executeUpdate("DELETE FROM learnedWords");

                for(var word:words) {
                    addLearnedWord(id++,word);
                }
            }
}
