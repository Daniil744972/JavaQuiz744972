package com.example.javaquiz744972.controllers;

import com.example.javaquiz744972.QuestionsReader;
import com.example.javaquiz744972.entities.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;

public class QuizController {
    @FXML
    private VBox mainbox;

    private Quiz quiz;


    public void loadQuiz(ActionEvent actionEvent) throws IOException {
        QuestionsReader questionsReader = new QuestionsReader();
        quiz = questionsReader.ReadQuiz();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/javaquiz744972/StartMenu.fxml"));
        VBox startMenu = fxmlLoader.load();

        StartMenuController startMenuController = fxmlLoader.getController();
        startMenuController.setQuiz(quiz);

        mainbox.getChildren().clear();
        mainbox.getChildren().add(startMenu);
    }
}