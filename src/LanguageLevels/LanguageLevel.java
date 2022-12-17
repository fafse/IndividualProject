package LanguageLevels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LanguageLevel {
    private final Connection conn;
    private final String level;
    private final String connectionUrl;
    static private final String user = "sa";
    static private final String password = "";

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public String getLevel() {
        return level;
    }

    public Connection getConn() {
        return conn;
    }

    public LanguageLevel(String level, String connectionUrl) throws SQLException {
        this.level = level;
        this.connectionUrl= "jdbc:h2:"+connectionUrl;
        this.conn = DriverManager.getConnection(this.connectionUrl, user, password);
    }
}
