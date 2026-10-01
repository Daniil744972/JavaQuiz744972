package com.example.javaquiz744972.controllers;

import com.example.javaquiz744972.QuestionsReader;
import com.example.javaquiz744972.entities.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizController {
    @FXML
    private VBox mainbox;

    private Quiz quiz;
    private List<Node> initialContent;

    @FXML
    public void initialize() {
        initialContent = new ArrayList<>(mainbox.getChildren());
    }

    public void loadQuiz(ActionEvent actionEvent) throws IOException {
        QuestionsReader questionsReader = new QuestionsReader();
        quiz = questionsReader.ReadQuiz();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/javaquiz744972/StartMenu.fxml"));
        VBox startMenu = fxmlLoader.load();

        QuestionController questionController = fxmlLoader.getController();
        questionController.setQuiz(quiz);
        questionController.SetQuizController(this);

        mainbox.getChildren().clear();
        mainbox.getChildren().add(startMenu);
    }

    public void resetToMenu() throws IOException {
        mainbox.getChildren().clear();
        mainbox.getChildren().addAll(initialContent);

    }
}