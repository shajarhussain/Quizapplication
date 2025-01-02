package com.example.quizapplication;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentQuizController {

    @FXML
    private ImageView questionImage; // Image view for question images

    @FXML
    private Button BackButton;
    @FXML
    private Label questionLabel; // Label for displaying the current question

    @FXML
    private RadioButton optionA; // Option A for the question
    @FXML
    private RadioButton optionB; // Option B for the question
    @FXML
    private RadioButton optionC; // Option C for the question
    @FXML
    private RadioButton optionD; // Option D for the question

    @FXML
    private Button nextButton; // Button to go to the next question
    @FXML
    private Button submitButton; // Button to submit the quiz

    @FXML
    private Label timerLabel; // Label to display remaining time

    private int currentQuestion = 0; // To keep track of the current question number
    private int score = 0; // To store the score
    private int timeLeft = 15; // Time left for the current question

    private final ToggleGroup group = new ToggleGroup(); // Toggle group for radio buttons

    private final String[] questions = {
            "What is the fundamental principle of Object-Oriented Programming?",
            "Which of the following is an access specifier in C++?",
            "Which of the following defines encapsulation?",
            "What is the purpose of a constructor in C++?",
            "What is polymorphism in OOP?",
            "What does inheritance allow in C++?",
            "What is a virtual function in C++?",
            "Which operator is used to access members of a class through a pointer?",
            "What is the default access specifier for members of a class in C++?",
            "Which of the following concepts allows different functions with the same name to be called?"
    };

    private final String[][] options = {
            {"Functions", "Classes and Objects", "Arrays", "Pointers"},
            {"protected", "private", "public", "All of the above"},
            {"Wrapping data and functions into a single unit", "Deriving new classes from base classes", "Using objects to access data", "Hiding data from outside access"},
            {"To destroy an object", "To initialize an object", "To overload operators", "To manage memory"},
            {"The ability to have multiple forms", "Using different names for the same function", "Hiding data from external access", "Reusing code in derived classes"},
            {"Reuse of code", "Creation of objects", "Encapsulation of data", "Overloading of operators"},
            {"A function that cannot be overridden", "A function defined in the base class and redefined in the derived class", "A function that exists only at runtime", "A function with the keyword static"},
            {".", "->", "::", "&"},
            {"private", "public", "protected", "None of the above"},
            {"Polymorphism", "Function overloading", "Inheritance", "Encapsulation"}
    };

    private final int[] correctAnswers = {1, 3, 0, 1, 0, 0, 1, 1, 0, 1}; // Correct answers (indices)


    private Timeline timer; // Timer object

    @FXML
    public void initialize() {
        // Set initial question and options
        updateQuestion();
        BackButton.setVisible(false);
        // Group radio buttons
        optionA.setToggleGroup(group);
        optionB.setToggleGroup(group);
        optionC.setToggleGroup(group);
        optionD.setToggleGroup(group);

        // Disable submit button initially
        submitButton.setDisable(true);

        // Add listener to enable Next button when an option is selected
        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            nextButton.setDisable(newValue == null);
        });

        // Initialize and start the timer
        initializeTimer();
    }

    private void initializeTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + " sec");

            if (timeLeft <= 0) {
                handleNextButton(); // Auto-submit the answer and go to the next question
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void resetTimer() {
        timeLeft = 15; // Reset the timer to 15 seconds
        timerLabel.setText("Time Left: " + timeLeft + " sec");
    }

    private void updateQuestion() {
        if (currentQuestion < questions.length) {
            // Set the current question text
            questionLabel.setText(questions[currentQuestion]);

            // Set the options for the radio buttons
            optionA.setText(options[currentQuestion][0]);
            optionB.setText(options[currentQuestion][1]);
            optionC.setText(options[currentQuestion][2]);
            optionD.setText(options[currentQuestion][3]);

            // Reset the previous selection
            group.selectToggle(null);
            nextButton.setDisable(true); // Disable next button until an option is selected

            // Reset the timer
            resetTimer();
        } else {
            showResults();
        }
    }

    @FXML
    private void handleNextButton() {
        // Check if the selected answer is correct
        if (isAnswerCorrect()) {
            score++;
        }

        // Move to the next question
        currentQuestion++;

        // Update question or show results
        if (currentQuestion < questions.length) {
            updateQuestion();
        } else {
            showResults();
        }
        if(questions.length-2==currentQuestion){
            nextButton.setVisible(false);
            submitButton.setVisible(true);
            submitButton.setDisable(false);
        }
    }

    private boolean isAnswerCorrect() {
        int selectedAnswer = group.getToggles().indexOf(group.getSelectedToggle());
        return selectedAnswer == correctAnswers[currentQuestion];
    }

    @FXML
    private void handleSubmitButton() {
        // Check if the last answer is correct
        if (isAnswerCorrect()) {
            score++;
        }

        showResults();
        BackButton.setVisible(true);
        saveScoreToDatabase();
        timer.stop(); // Stop the timer when the quiz ends
    }


    private void showResults() {
        questionLabel.setText("Quiz Completed! Your score: " + score + "/" + questions.length);
        nextButton.setDisable(true);
        submitButton.setDisable(true);
        timer.stop(); // Stop the timer
    }
    private String username;

    // Method to set the username
    public void setUsername(String username) {
        this.username = username;


        System.out.println("Username received: " + username);
    }
    private void saveScoreToDatabase() {
        DatabaseConnection con = new DatabaseConnection();
        try (
                Connection connection = con.GetConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO results (Username, Result) VALUES (?, ?)"
                )
        ) {
            preparedStatement.setString(1, username); // Replace with actual username if available
            preparedStatement.setInt(2, score);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();

// Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("StudentDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

// Log any issues with the controller
        StudentDashboardController studentdashboardController = fxmlLoader.getController();
        System.out.println("Controller instance: " + studentdashboardController);

        if (studentdashboardController != null) {
            studentdashboardController.setUsername(username);
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
        Stage currentStage = (Stage) BackButton.getScene().getWindow();
        currentStage.close();
        stage.show();

    }
}
