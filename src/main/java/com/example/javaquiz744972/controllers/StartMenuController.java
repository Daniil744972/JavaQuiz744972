package com.example.javaquiz744972.controllers;

import com.example.javaquiz744972.entities.Question;
import com.example.javaquiz744972.entities.Quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.logging.Logger;

public class StartMenuController {

    @FXML
    private Label quizName;

    @FXML
    private Label description;

    private Quiz quiz;
    private int currentQuizIndex = 0;
    private Logger logger = Logger.getLogger(StartMenuController.class.getName());

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quizName.setText(quiz.getTitle());
        description.setText(quiz.getDescription());
    }

    @FXML
    public void nextQuestion(ActionEvent actionEvent) {
        if (currentQuizIndex< quiz.getPages().size()) {
            Question question = quiz.getPages().get(currentQuizIndex).getElements().get(0);
            currentQuizIndex++;
    logger.info(question.toString());
//            switch (question.getType()) {
//                case QuestionType.RADIOGROUP -> loadRadioQuestion();
//                case QuestionType.BOOLEAN -> loadBooleanQuestion();
//            }
        }
    }

    private void loadRadioQuestion(){
    System.out.println("Radio");
    }

    private void loadBooleanQuestion(){
        System.out.println("Boolean");
    }
}
