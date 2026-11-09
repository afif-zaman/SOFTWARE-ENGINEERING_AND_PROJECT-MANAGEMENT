
package com.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection()
            throws SQLException {

        String url = System.getProperty("db.url");
        String username = System.getProperty("db.user");
        String password = System.getProperty("db.password");

        if (url == null || username == null || password == null) {
            throw new SQLException(
                    "Database configuration is missing."
            );
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "MySQL JDBC driver not found.", e
            );
        }

        return DriverManager.getConnection(
                url,
                username,
                password
        );
    }
}
