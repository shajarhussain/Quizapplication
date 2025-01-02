package com.example.quizapplication;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class RoleSelectionController {
    @FXML
    private ComboBox<String> RoleCombobox;

    @FXML
    public void initialize() {
        // Populate the ComboBox with Admin and Student
        ObservableList<String> list = FXCollections.observableArrayList("Admin", "Student");
        RoleCombobox.setItems(list);
    }


    public void handleselection(ActionEvent actionEvent) throws IOException {
        String selectedRole = RoleCombobox.getValue();

        // Close the current stage
        Stage currentStage = (Stage) RoleCombobox.getScene().getWindow();
        currentStage.close();

        // Pass the selected role to the next stage
        FXMLLoader fxmlLoader = new FXMLLoader(RoleSelectionController.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Access the controller of the next stage
        LoginController loginController = fxmlLoader.getController();
        loginController.setRole(selectedRole); // Pass the selected role

        // Show the next stage
        Stage stage = new Stage();
        stage.setTitle("Quiz Application");
        stage.setMaxHeight(400);
        stage.setMaxWidth(600);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
    }
}
