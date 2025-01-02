package com.example.quizapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private ComboBox<String> comboBoxList;

    @FXML
    public void initialize() {
        // Populate the ComboBox with Admin and Student
        ObservableList<String> list = FXCollections.observableArrayList("Admin", "Student");
        comboBoxList.setItems(list);
    }
    }
