package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Vulnerable Demo App
 */
public class App 
{
    // Hardcoded credentials (Security Issue)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASS = "password123"; // Sonar will flag this

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        // Input not validated (Security Issue)
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        // SQL Injection vulnerability
        String query = "SELECT * FROM users WHERE username = '" + username + "'";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            // Executing unsafe query
            stmt.executeQuery(query);

            // Resource leak (no close)
        } catch (Exception e) {
            // Sensitive info exposure
            e.printStackTrace();
        }

        // Infinite loop (bad practice)
        while(true) {
            System.out.println("Running...");
        }
    }
}
