package com.example.quizapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDashboardController {
    // Logger for this class
    private static final Logger LOGGER = Logger.getLogger(StudentDashboardController.class.getName());
    public Button startQuizButton;

    // Method to handle the "Start Quiz" button
    public void handlestartQuiz(ActionEvent actionEvent) {

    }
    @FXML
    private Button BackButton;

    private String Username;
    public void setUsername(String propUsername){Username=propUsername;
    System.out.println(Username);}
    public void handlestartquiz(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the quiz screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentQuiz.fxml"));
            Parent quizRoot = loader.load();

            // Retrieve the controller from the FXMLLoader
            StudentQuizController studentQuizController = loader.getController();

            // Pass the username to the StudentQuizController
            studentQuizController.setUsername(this.Username);

            // Get the current stage from the action event
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Set the new scene (quiz screen) on the stage
            Scene quizScene = new Scene(quizRoot);
            stage.setScene(quizScene);
            stage.setTitle("Quiz Application - Quiz");

            // Show the updated stage
            stage.show();

            // Log success
            LOGGER.info("Navigated to the Quiz screen with username: " + this.Username);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load the quiz screen.", e);

            // User-friendly feedback (for future use, you can add an alert dialog here)
            System.out.println("Error loading the quiz screen. Please try again.");
        }

    }

   public void handleviewprogress(ActionEvent actionEvent)
    {
        try {
            // Load the FXML file for the Admin Dashboard screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
            Parent adminDashboardRoot = loader.load();

            // Get the controller for AdminDashboard
            AdminDashboardController controller = loader.getController();

            // Set the title label's text to "My Result"
            controller.setTitleLabelText("Results");
            // Retrieve the controller from the FXMLLoader
            AdminDashboardController adminQuizController = loader.getController();

            // Pass the username to the StudentQuizController
            adminQuizController.setUsername(this.Username);

            // Get the current stage from the action event
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // Set the new scene (Admin Dashboard screen) on the stage
            Scene adminDashboardScene = new Scene(adminDashboardRoot);
            stage.setScene(adminDashboardScene);
            stage.setTitle("Quiz Application");

            // Show the updated stage
            stage.show();

            // Log success
            LOGGER.info("Navigated to the Admin Dashboard screen.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load the Admin Dashboard screen.", e);

            // User-friendly feedback (for future use, you can add an alert dialog here)
            System.out.println("Error loading the Admin Dashboard screen. Please try again.");
        }

    }

    public void handlebackbutton(ActionEvent actionEvent) throws IOException {
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
        Stage currentStage = (Stage)BackButton .getScene().getWindow();
        currentStage.close();
        stage.show();

    }
}