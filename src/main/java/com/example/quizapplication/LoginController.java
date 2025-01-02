package com.example.quizapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    TextField UsernameTextbox;
    @FXML
    TextField PasswordTextbox;

    public String name;
    private String Role;
    public void setRole(String role) {
        // Display the role or use it as needed
        if (role != null) {
            Role=role;
            System.out.println(Role);
        }
    }
    public void handleloginbutton(ActionEvent actionEvent) throws IOException {
        int RoleID=1;
        if(Role==null){
            showAlert("Role Selection","No Role Selection");
            Stage currentStage = (Stage) PasswordTextbox.getScene().getWindow();
            currentStage.close();
            Stage stage=new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("role_selection.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            StudentDashboardController cu=new StudentDashboardController();
            cu.setUsername(UsernameTextbox.getText());


            stage.setTitle("Hello!");
            stage.setMaxHeight(400);
            stage.setMaxWidth(600);
            stage.setMinHeight(400);
            stage.setMinWidth(600);
            stage.setScene(scene);
            stage.show();
            return;
        }
        if (Role.equals("Student")) {
            RoleID=2;
        }
        else if(Role.equals("Admin")){
            RoleID=1;
        }
        else{
            showAlert("Role Selection","No Role Selection");
            Stage currentStage = (Stage) PasswordTextbox.getScene().getWindow();
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
        boolean flag=false;
        if(!(UsernameTextbox.getText().isEmpty())&&!(PasswordTextbox.getText().isEmpty())){
             name = UsernameTextbox.getText();
            DatabaseConnection con=new DatabaseConnection();
            try(
                    Connection connection=con.GetConnection();
                    PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM users WHERE Username = ? AND Password = ? AND Roles = ?")
                    ) {
                preparedStatement.setString(1,UsernameTextbox.getText());
                preparedStatement.setString(2,PasswordTextbox.getText());
                preparedStatement.setInt(3,RoleID);
                ResultSet resultSet= preparedStatement.executeQuery();
                flag=resultSet.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if(flag) {
                if (RoleID == 1) {
                    Stage currentStage = (Stage) PasswordTextbox.getScene().getWindow();
                    currentStage.close();
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AdminDashboard.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setTitle("Admin");
                    stage.setMaxHeight(400);
                    stage.setMaxWidth(600);
                    stage.setMinHeight(400);
                    stage.setMinWidth(600);
                    stage.setScene(scene);
                    stage.show();
                } else if (RoleID == 2) {
                    Stage stage = new Stage();

// Load the FXML file
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StudentDashboard.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());

// Log any issues with the controller
                    StudentDashboardController studentdashboardController = fxmlLoader.getController();
                    System.out.println("Controller instance: " + studentdashboardController);

                    if (studentdashboardController != null) {
                        studentdashboardController.setUsername(UsernameTextbox.getText());
                    } else {
                        System.err.println("StudentDashboardController is null!");
                        return; // Prevent further execution
                    }

// Set stage properties
                    stage.setTitle("Student");
                    stage.setMaxHeight(400);
                    stage.setMaxWidth(600);
                    stage.setMinHeight(400);
                    stage.setMinWidth(600);
                    stage.setScene(scene);
                    Stage currentStage = (Stage) PasswordTextbox.getScene().getWindow();
                    currentStage.close();
                    stage.show();




                } else {
                    showAlert("Role Selection", "No Role Selection");
                }
            }
            else{
                showAlert("Authentication", "Enter Valid Username and Password");
            }
        }
        else{
            showAlert("Error","Username or Password is Empty");
        }

    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void handlesignupbutton(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) PasswordTextbox.getScene().getWindow();
        currentStage.close();

        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Signup.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        SignupController signupController = fxmlLoader.getController();
        signupController.setRole(Role);
        stage.setTitle("Signup");
        stage.setMaxHeight(400);
        stage.setMaxWidth(600);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
    }
}
