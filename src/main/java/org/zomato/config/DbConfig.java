package org.zomato.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/zomato";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
