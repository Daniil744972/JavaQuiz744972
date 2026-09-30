package com.example.javaquiz744972.controllers;

import com.example.javaquiz744972.entities.BooleanQuestion;
import com.example.javaquiz744972.entities.Question;
import com.example.javaquiz744972.entities.Quiz;
import com.example.javaquiz744972.entities.RadioQuestion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.logging.Logger;

public class StartMenuController {

    @FXML
    private Label quizName;

    @FXML
    private Label description;
    @FXML
    private VBox questionBox;

    private Quiz quiz;
    private int currentQuizIndex = 0;

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quizName.setText(quiz.getTitle());
        description.setText(quiz.getDescription());
    }

    @FXML
    public void nextQuestion(ActionEvent actionEvent) {
        description.setText("");
        if (currentQuizIndex< quiz.getPages().size()) {
            Question question = quiz.getPages().get(currentQuizIndex).getElements().get(0);
            currentQuizIndex++;
            switch (question.getType()) {
                case "radiogroup" -> loadRadioQuestion((RadioQuestion) question);
                case "boolean" -> loadBooleanQuestion((BooleanQuestion) question);
            }
        }
    }

    private void loadRadioQuestion(RadioQuestion question) {
        questionBox.getChildren().clear();

        Label titleLabel = new Label(question.getTitle());
        titleLabel.setWrapText(true);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        questionBox.getChildren().add(titleLabel);

        ToggleGroup group = new ToggleGroup();
        for (String choice : question.getChoices()) {
            RadioButton rb = new RadioButton(choice);
            rb.setToggleGroup(group);
            questionBox.getChildren().add(rb);
        }
    }

    private void loadBooleanQuestion(BooleanQuestion question) {
        questionBox.getChildren().clear();

        Label titleLabel = new Label(question.getTitle());
        titleLabel.setWrapText(true);
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        questionBox.getChildren().add(titleLabel);

        ToggleGroup group = new ToggleGroup();
        RadioButton trueRadioButton = new RadioButton(question.getLabelTrue());
        RadioButton falseRadioButton = new RadioButton(question.getLabelFalse());
        trueRadioButton.setToggleGroup(group);
        falseRadioButton.setToggleGroup(group);

        questionBox.getChildren().addAll(trueRadioButton, falseRadioButton);
    }
}
