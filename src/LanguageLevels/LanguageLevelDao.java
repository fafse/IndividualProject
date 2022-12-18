package LanguageLevels;

import Checkers.Checkers;
import Exceptions.LanguageLevelNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LanguageLevelDao {
    private final List<LanguageLevel> levels = new ArrayList<LanguageLevel>();
    private String pathToFolder="words\\";

    public LanguageLevelDao() throws SQLException {
        levels.add(new LanguageLevel("A1"));

        levels.add(new LanguageLevel("A2"));

        levels.add(new LanguageLevel("B1"));

        levels.add(new LanguageLevel("B2"));

        levels.add(new LanguageLevel("C1"));

        levels.add(new LanguageLevel("C2"));
    }



    private void update(Statement statement,String tableName, int first, String second, String third) throws SQLException {
            statement.execute("INSERT INTO " + tableName +  " VALUES" +
                    "(" + first + ", " + second + ", "+third+")");
    }

    private LanguageLevel findByLevel(String level)
    {
        for(var languageLevel:levels)
        {
            if(languageLevel.getLevel().equals(level))
            {
                return languageLevel;
            }
        }
        return null;
    }

    public String getWord(String level) throws LanguageLevelNotFoundException, SQLException {
        LanguageLevel tmp = findByLevel(level);
        int randomId = (int) ((Math.random() * ((tmp.getNumWords() - 1) + 1)) + 1);
        Statement statement = tmp.getStatement();
        if(statement==null) {
            throw new LanguageLevelNotFoundException("Unavailable to find " + level);
        }
        try (ResultSet result = statement.executeQuery("SELECT * FROM "+level+" " +
                "WHERE ID = " + "'"+randomId+"'")) {
            if (result.next()) {
                return result.getString("WORD") + "\t" + result.getString("TRANSLATION");
            } else {
                return null;
            }
        }
    }

    public String getHigherLevel(String level)
    {
        LanguageLevel tmp = findByLevel(level);
        int indexHigherLevel = levels.indexOf(tmp)+1;
        if(indexHigherLevel>=levels.size()) {
            return level;
        }else {
            return levels.get(indexHigherLevel).getLevel();
        }
    }

    public void filltables() throws IOException, SQLException, LanguageLevelNotFoundException {

        for(var level:levels) {
            if (!Checkers.Existance.tableExists(pathToFolder + level.getLevel() + ".txt")) {

                Statement statement = level.getStatement();
                if (statement == null) {
                    throw new LanguageLevelNotFoundException("Unavailable to find " + level);
                }
                int id = 1;
                statement.executeUpdate("DELETE FROM " + level.getLevel());
                BufferedReader reader = new BufferedReader(new FileReader(pathToFolder + level.getLevel() + ".txt"));
                String line = reader.readLine();
                while (line != null) {
                    String[] fields = line.split("\t");
                    update(level.getStatement(), level.getLevel(), id++, "'" + fields[0] + "'", "'" + fields[1] + "'");
                    line = reader.readLine();
                }
            }
        }
    }

}
