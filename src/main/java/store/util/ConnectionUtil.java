package store.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static final String URL = "INSERT YOUR DATABASE URL HERE";
    private static final String USERNAME = "INSERT YOUR MYSQL USERNAME HERE";
    private static final String PASSWORD = "INSERT YOUR MYSQL PASSWORD HERE";
    private static final String JDBC_DRIVER = "INSERT YOUR DRIVER HERE";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find an SQL Driver!", e);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.setProperty("user", USERNAME);
        dbProperties.setProperty("password", PASSWORD);
        try {
            return DriverManager.getConnection(URL, dbProperties);
        } catch (SQLException e) {
            throw new RuntimeException("Can't create connection to a DB!", e);
        }
    }
}
