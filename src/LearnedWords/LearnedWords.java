package LearnedWords;

import java.sql.*;

public class LearnedWords {
    private final Connection conn;

    private final String connectionUrl;
    static private final String user = "sa";
    static private final String password = "";
    private final Statement statement;

    public LearnedWords() throws SQLException {
        this.connectionUrl="jdbc:h2:/DataBase/learnedWords";
        this.conn = DriverManager.getConnection(this.connectionUrl, user, password);
        this.statement = conn.createStatement();
        String createOrders = "create table if not exists learnedWords " +
                "(ID INT PRIMARY KEY AUTO_INCREMENT, "+
                "WORD VARCHAR NOT NULL, " +
                "TRANSLATION varchar NOT NULL)";
        statement.execute(createOrders);
    }
    public Statement getStatement() {
        return statement;
    }
    public String getConnectionUrl() {
        return connectionUrl;
    }


    public Connection getConn() {
        return conn;
    }

    public int getNumWords() throws SQLException {
        try (ResultSet result = statement.executeQuery("SELECT MAX(ID) AS MAX FROM learnedWords")) {
            if (result.next()) {
                return result.getInt(1);
            } else {
                return 0;
            }

        }
    }
}
