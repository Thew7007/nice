package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Downloads\\111\\FinalProject\\src\\main\\resources\\sqlite.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(URL);
    }
}