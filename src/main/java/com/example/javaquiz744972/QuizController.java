package com.example.javaquiz744972;

import com.example.javaquiz744972.entities.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class QuizController {

    @FXML
    private Label quizName;

    @FXML
    private Label description;

    private Quiz quiz;

    @FXML
    public void initialize() throws IOException {
        startQuiz();
    }

    private void startQuiz() throws IOException {
        QuestionsReader questionsReader = new QuestionsReader();

        quiz = questionsReader.ReadQuiz();

        quizName.setText(quiz.getTitle());
        description.setText(quiz.getDescription());
    }

    @FXML
    public void openQuestion(ActionEvent actionEvent) {
        System.out.println("Open question");
    }
}