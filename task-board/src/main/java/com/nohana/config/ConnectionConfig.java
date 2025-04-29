package com.nohana.config;

import lombok.NoArgsConstructor;
import static lombok.AccessLevel.PRIVATE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor(access = PRIVATE)
public class ConnectionConfig {
    
    public static Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://localhost/board";
        String USER = "root";
        String PASS = "admin";

        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        conn.setAutoCommit(false);
        return conn;
    }
}
