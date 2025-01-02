package com.example.quizapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ResultsdisplayController {
    @FXML
    private TableView<Users> resultsTable;
    @FXML
    private TableColumn<Users, String> studentsColumn;
    @FXML
    private TableColumn<Users, Integer> resultsColumn;


    private ObservableList<Users> userList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind columns to Users properties
        studentsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        resultsColumn.setCellValueFactory(new PropertyValueFactory<>("result"));

        // Load data from the database
        LoadDatabase loadDatabase = new LoadDatabase();
        loadDatabase.loadresults();

        // Populate the ObservableList from the LoadDatabase class
        userList.addAll(loadDatabase.getMyUsers());

        // Set items to the TableView
        resultsTable.setItems(userList);
    }


}
