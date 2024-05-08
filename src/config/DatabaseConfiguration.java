package config;

import java.sql.*;
public class DatabaseConfiguration{
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "testuser";
    private static final String PASSWROD = "corina123";

    private static Connection databaseConnection;

    private DatabaseConfiguration() {
    }

    public static Connection getConnection() {
        try {
            if (databaseConnection == null || databaseConnection.isClosed()) {
                databaseConnection = DriverManager.getConnection(URL, USER, PASSWROD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseConnection;
    }
}