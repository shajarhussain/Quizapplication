package com.example.quizapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoadDatabase {
    public List<Users> my_users = new ArrayList<>();

    public void loadresults() {
        DatabaseConnection connection = new DatabaseConnection();
        try (
                Connection con = connection.GetConnection();
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM results");
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("Username");
                int result = resultSet.getInt("Result");
                Users user = new Users(name, result);
                my_users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Users> getMyUsers() {
        return my_users;
    }
}
