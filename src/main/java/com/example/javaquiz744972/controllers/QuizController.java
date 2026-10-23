package com.example.javaquiz744972.controllers;

import com.example.javaquiz744972.GameManager;
import com.example.javaquiz744972.observer.GameObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizController implements GameObserver {
    @FXML
    private VBox mainbox;
    @FXML
    private Label error;

    private List<Node> initialContent;
    private GameManager manager = GameManager.getInstance();

    @FXML
    public void initialize() {
        initialContent = new ArrayList<>(mainbox.getChildren());
        manager.addObserver(this);
    }

    public void loadQuiz(ActionEvent actionEvent) {
        try {
            manager.loadQuiz();
            if (GameManager.getInstance().getQuiz() == null) return;
            getNameUI();
        } catch (Exception e) {
            error.setText(e.getMessage());
        }

    }

    private void getNameUI() {
        mainbox.getChildren().clear();
        TextField nameField = new TextField();
        if (manager.getUserName() != null) {
            nameField.setText(manager.getUserName());
        } else {
            nameField.setPromptText("Enter your name");
        }
        nameField.setMaxWidth(250);

        Button startquiz = new Button("Start");
        startquiz.setId("startQuiz");

        startquiz.setOnAction((event) -> {
            String name = nameField.getText();
            if (manager.getUserName() == null && name.isEmpty()) {
                error.setText("Please enter your name");
                return;
            }
            manager.setUserName(name);
            try {
                startQuiz();
            } catch (IOException e) {
                error.setText(e.getMessage());
            }
        });
        mainbox.getChildren().addAll(nameField, error, startquiz);
    }

    private void startQuiz() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/javaquiz744972/StartMenu.fxml"));
        VBox startMenu = fxmlLoader.load();
        mainbox.getChildren().clear();
        mainbox.getChildren().add(startMenu);
    }

    public void onGameReset() {
        mainbox.getChildren().clear();
        mainbox.getChildren().addAll(initialContent);

    }
}