package LanguageLevels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LanguageLevelDao {
    private final List<LanguageLevelDao> levels = new ArrayList<LanguageLevelDao>();

    public LanguageLevelDao(String pathToFolder,String dataBasePath) throws SQLException {
        LanguageLevelDao levelB1 = new LanguageLevelDao("B1",dataBasePath+ "B1");
        levelB1.initLevel(pathToFolder);
        levels.add(levelB1);

        LanguageLevelDao levelB2 = new LanguageLevelDao("B2",dataBasePath+ "B2");
        levelB2.initLevel(pathToFolder);
        levels.add(levelB2);

        LanguageLevelDao levelA2 = new LanguageLevelDao("A2",dataBasePath+ "A2");
        levelA2.initLevel(pathToFolder);
        levels.add(levelA2);

        LanguageLevelDao levelA1 = new LanguageLevelDao("A1",dataBasePath+ "A1");
        levelA1.initLevel(pathToFolder);
        levels.add(levelA1);

        LanguageLevelDao levelC1 = new LanguageLevelDao("C1",dataBasePath+ "C1");
        levelC1.initLevel(pathToFolder);
        levels.add(levelC1);

        LanguageLevelDao levelC2 = new LanguageLevelDao("C2",dataBasePath+ "C2");
        levelC2.initLevel(pathToFolder);
        levels.add(levelC2);
    }

    public void initLevel(String pathToFolder,String level) throws SQLException {
        createTable();
        try {
            filltable(pathToFolder+ level+".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void createTable(String level) throws SQLException {
        Connection conn = languageLevel.getConn();
        Statement statement = null;
            statement = conn.createStatement();
        String createOrders = "create table if not exists "+languageLevel.getLevel()+" " +
                    "(WORD VARCHAR NOT NULL, " +
                    "TRANSLATION varchar NOT NULL)";
            statement.execute(createOrders);
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
