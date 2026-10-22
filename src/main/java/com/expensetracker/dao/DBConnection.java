package com.expensetracker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    // File-based H2 database. The .mv.db file is created automatically
    // in the server's working directory the first time the app runs.
    private static final String URL = "jdbc:h2:./expensetrackerdb;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");
            initDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    private static void initDatabase() throws SQLException {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS expenses (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "description VARCHAR(255) NOT NULL, " +
                "category VARCHAR(100) NOT NULL, " +
                "amount DECIMAL(10,2) NOT NULL, " +
                "expense_date DATE NOT NULL" +
                ")"
            );
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
