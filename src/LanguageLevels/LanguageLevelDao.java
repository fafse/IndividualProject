package LanguageLevels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LanguageLevelDao {
    private final List<LanguageLevel> levels = new ArrayList<LanguageLevel>();
    private String pathToFolder;

    public LanguageLevelDao(String pathToFolder,String dataBasePath) throws SQLException {
        this.pathToFolder=pathToFolder;
        levels.add(new LanguageLevel("A1",dataBasePath));

        levels.add(new LanguageLevel("A2",dataBasePath));

        levels.add(new LanguageLevel("B1",dataBasePath));

        levels.add(new LanguageLevel("B2",dataBasePath));

        levels.add(new LanguageLevel("C1",dataBasePath));

        levels.add(new LanguageLevel("C2",dataBasePath));
    }



    public void update(Connection connection, String tableName, String first, String second) throws SQLException {
        try (Statement statement = connection.createStatement()){
            statement.execute("INSERT INTO " + tableName +  " VALUES" +
                    "(" + first + ", " + second + ")");
        }
    }

    public void filltable(String pathFile,String level) throws IOException, SQLException {
        Connection conn = languageLevel.getConn();
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("DELETE FROM " + languageLevel.getLevel());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        BufferedReader reader = new BufferedReader(new FileReader(pathFile));
        String line = reader.readLine();
        while (line != null) {
            String[] fields = line.split("\t");
            update(conn, languageLevel.getLevel(), "'" + fields[0]+"'", "'" + fields[1] + "'");
            line = reader.readLine();
        }
    }

}
