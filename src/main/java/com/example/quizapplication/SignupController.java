package com.example.quizapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupController {
    @FXML
    private TextField UsernameTextField;
    @FXML
    private PasswordField PasswordTextField;

    public String Role;
    public void setRole(String role) {
        // Display the role or use it as needed
        if (role != null) {
            Role=role;
            System.out.println(Role);
        }
    }

    public void handleSignupbutton(ActionEvent actionEvent) throws IOException {
        int RoleID=1;
        if(Role==null){
            showAlert("Role Selection","No Role Selection");
            Stage currentStage = (Stage) PasswordTextField.getScene().getWindow();
            currentStage.close();
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("role_selection.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello!");
            stage.setMaxHeight(400);
            stage.setMaxWidth(600);
            stage.setMinHeight(400);
            stage.setMinWidth(600);
            stage.setScene(scene);
            stage.show();
            return;
        }
        if(Role.equals("Admin")){
            RoleID=1;
        }
        else if(Role.equals("Student")){
            RoleID=2;
        }
        else{
            showAlert("Role Selection","No Role Selection");
            Stage currentStage = (Stage) PasswordTextField.getScene().getWindow();
            currentStage.close();
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("role_selection.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello!");
            stage.setMaxHeight(400);
            stage.setMaxWidth(600);
            stage.setMinHeight(400);
            stage.setMinWidth(600);
            stage.setScene(scene);
            stage.show();
            return;
        }
        if(!(UsernameTextField.getText().isEmpty())&&!(PasswordTextField.getText().isEmpty())) {
            DatabaseConnection con = new DatabaseConnection();
            try (Connection connection = con.GetConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(Username,Password,Roles) VALUES(?,?,?)");) {
                preparedStatement.setString(1, UsernameTextField.getText());
                preparedStatement.setString(2, PasswordTextField.getText());
                preparedStatement.setInt(3,RoleID );
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Successfully Added a new user");
            Stage currentStage = (Stage) PasswordTextField.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("role_selection.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello!");
            stage.setMaxHeight(400);
            stage.setMaxWidth(600);
            stage.setMinHeight(400);
            stage.setMinWidth(600);
            stage.setScene(scene);
            stage.show();
        }
        else{
            showAlert("Error", "Username or Password is Empty");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handleLoginButton(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();

// Load the FXML file

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

// Set stage properties
        stage.setTitle("View Result");
        stage.setMaxHeight(400);
        stage.setMaxWidth(600);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        Stage currentStage = (Stage) UsernameTextField .getScene().getWindow();
        currentStage.close();
        stage.show();

    }
}
