package com.example.quizapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminDashboardController {

    @FXML
    private Button BackButton;

    @FXML
    private Label TitleLabel;
    @FXML
    private Button viewResultButton;

    @FXML
    private TableView<Users> resulttable;

    @FXML
    private TableColumn<Users, String> UsernameColumn;

    @FXML
    private TableColumn<Users, Integer> ResultColumn;


    private String Username;
    public void setUsername(String propUsername){Username=propUsername;
        System.out.println(Username);}

    public void setTitleLabelText(String text) {
        if (TitleLabel != null) {
            TitleLabel.setText(text);
        }
    }
    // Initialize method to set up the table view
    public void initialize() {
        viewResultButton.setVisible(true);
        resulttable.setVisible(false);

        // Set up columns to match Users class properties
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ResultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
    }

    // Method to handle viewResults button
    public void handleviewResults(ActionEvent actionEvent) {
        viewResultButton.setVisible(false);
        resulttable.setVisible(true);

        // Load data from database
        LoadDatabase loader = new LoadDatabase();
        loader.loadresults();

        // Convert the list to an ObservableList
        List<Users> usersList = loader.getMyUsers();
        ObservableList<Users> observableList = FXCollections.observableArrayList(usersList);

        // Set the ObservableList to the TableView
        resulttable.setItems(observableList);
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
        Stage currentStage = (Stage) BackButton.getScene().getWindow();
        currentStage.close();
        stage.show();

    }
}
