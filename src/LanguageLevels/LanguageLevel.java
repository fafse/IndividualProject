package LanguageLevels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LanguageLevel {
    private final Connection conn;
    private final String level;
    private final String connectionUrl;
    static private final String user = "sa";
    static private final String password = "";

    public LanguageLevel(String level, String connectionUrl) throws SQLException {
        this.level = level;
        this.connectionUrl= "jdbc:h2:"+connectionUrl;
        this.conn = DriverManager.getConnection(this.connectionUrl, user, password);
        Statement statement = null;
        statement = conn.createStatement();
        String createOrders = "create table if not exists "+level+" " +
                "(WORD VARCHAR NOT NULL, " +
                "TRANSLATION varchar NOT NULL)";
        statement.execute(createOrders);
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public String getLevel() {
        return level;
    }

    public Connection getConn() {
        return conn;
    }



}
