package com.example.quizapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String Url = "jdbc:mysql://localhost:3306/quizapplication";
    private static final String Username = "root";
    private static final String Password = "258369147Fun@";

    public Connection GetConnection() throws SQLException {
        return DriverManager.getConnection(Url, Username, Password);
    }
}
