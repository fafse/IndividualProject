package LanguageLevels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
public class LanguageLevel {
    private final String level;
    private final String connectionUrl = "jdbc:h2:/DataBaseFiles/ShopDataBase10";
    static private final String user = "sa";
    static private final String password = "";

    public LanguageLevel(String level) {
        this.level = level;
    }

    public void createTable()
    {
        try(Connection conn = DriverManager.getConnection(connectionUrl, user, password)){
            Statement statement = conn.createStatement();
            String createOrders = "create table if not exists orders " +
                    "(numberOrder integer NOT NULL, " +
                    "name varchar(40) NOT NULL, " +
                    "articleNumber varchar(40) NOT NULL)";
            statement.execute(createOrders);
            statement.execute("create table if not exists item(nameGoods varchar, articleNumber varchar primary key, " +
                    "price int)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void update(Connection connection, String tableName, String first, String second, String third) throws SQLException {
        try (Statement statement = connection.createStatement()){
            statement.execute("INSERT INTO " + tableName +  " VALUES" +
                    "(" + first + ", " + second + ", " + third+")");
        }
    }
    public static boolean isAdded(Connection connection, String article) throws SQLException {
        try (Statement statement = connection.createStatement()){
            try (ResultSet result = statement.executeQuery("SELECT * FROM ITEM " +
                    "WHERE articleNumber = " + article)) {
                if (result.next()) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }

    public void filltable(String pathFile) throws IOException {
        try(Connection conn = DriverManager.getConnection(connectionUrl, user, password)){
            BufferedReader reader = new BufferedReader(new FileReader(pathFile));
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                update(conn, "orders", fields[0], "'" + fields[1] + "'","'" +  fields[2] + "'");
                if(!isAdded(conn, "'" + fields[2] + "'")) {
                    update(conn, "ITEM",  "'" + fields[3] + "'","'" +  fields[2] + "'", fields[4]);
                }
                line = reader.readLine();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
