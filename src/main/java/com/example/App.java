package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Vulnerable Demo App
 */
public class App {

    // Hardcoded credentials (SonarQube will flag this)
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password123";

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // SQL Injection vulnerability
        String userInput = args.length > 0 ? args[0] : "defaultUser";
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

        try {
            // Dummy DB connection (may fail at runtime, but compiles fine)
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb", USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();

            // Dangerous execution
            stmt.execute(query);

        } catch (Exception e) {
            // Bad practice: printing stack trace
            e.printStackTrace();
        }

        // Code smell: unused variable
        int unused = 42;

        // Bad practice: empty catch block
        try {
            int x = 10 / 0;
        } catch (Exception e) {
        }
    }
}
