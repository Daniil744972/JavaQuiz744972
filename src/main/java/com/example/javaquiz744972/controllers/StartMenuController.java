package com.example.javaquiz744972.controllers;

import com.example.javaquiz744972.entities.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class StartMenuController {

    @FXML
    private Label quizName;

    @FXML
    private Label description;
    @FXML
    private VBox questionBox;

    private Quiz quiz;
    private int currentQuizIndex = 0;
    private Question currentQuestion;
    private ToggleGroup toggleGroup;
    private int rightAnswersCount = 0;

    private Label timerLabel;
    private Timeline timeline;
    private int timeLeft = 0;

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        quizName.setText(quiz.getTitle());
        description.setText(quiz.getDescription());
    }

    @FXML
    public void nextQuestion(ActionEvent actionEvent) {
        description.setText("");
        if (currentQuestion != null) {
            Toggle selectedToggle = toggleGroup.getSelectedToggle();
            if (selectedToggle == null) return;
            checkAnswer(selectedToggle);
        }
        if (currentQuizIndex< quiz.getPages().size()) {
            loadNextQuestion();
        }
    }

    private void loadNextQuestion() {
        Page page = quiz.getPages().get(currentQuizIndex);
        currentQuestion = page.getElements().get(0);
        currentQuizIndex++;
        switch (currentQuestion.getType()) {
            case "radiogroup" -> loadRadioQuestion((RadioQuestion) currentQuestion);
            case "boolean" -> loadBooleanQuestion((BooleanQuestion) currentQuestion);
        }
        startTimer(page.getTimeLimit());

    }

    private void checkAnswer(Toggle selectedToggle) {
        switch (currentQuestion.getType()) {
            case "boolean":
                BooleanQuestion booleanQuestion = (BooleanQuestion) currentQuestion;
                Boolean bAnswer = (Boolean) selectedToggle.getUserData();
                if (bAnswer.equals(booleanQuestion.isCorrectAnswer())){
                    rightAnswersCount++;
                }
                break;
            case "radiogroup":
                RadioQuestion radioQuestion = (RadioQuestion) currentQuestion;
                String sAnswer = (String) selectedToggle.getUserData();
                if(sAnswer.equals(radioQuestion.getCorrectAnswer())){
                    rightAnswersCount++;
                }
                break;
        }

    }

    private void loadRadioQuestion(RadioQuestion question) {
        questionBox.getChildren().clear();

        Label titleLabel = new Label(question.getTitle());
        titleLabel.setWrapText(true);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        questionBox.getChildren().add(titleLabel);
        questionBox.getChildren().add(createTopTimerHeader());

         toggleGroup = new ToggleGroup();
        for (String choice : question.getChoices()) {
            RadioButton rb = new RadioButton(choice);
            rb.setUserData(choice);
            rb.setToggleGroup(toggleGroup);
            questionBox.getChildren().add(rb);
        }
    }

    private void loadBooleanQuestion(BooleanQuestion question) {
        questionBox.getChildren().clear();

        Label titleLabel = new Label(question.getTitle());
        titleLabel.setWrapText(true);
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        questionBox.getChildren().add(titleLabel);
        questionBox.getChildren().add(createTopTimerHeader());

       toggleGroup = new ToggleGroup();

        RadioButton trueRadioButton = new RadioButton(question.getLabelTrue());
        trueRadioButton.setUserData(true);
        RadioButton falseRadioButton = new RadioButton(question.getLabelFalse());
        falseRadioButton.setUserData(false);

        trueRadioButton.setToggleGroup(toggleGroup);
        falseRadioButton.setToggleGroup(toggleGroup);

        questionBox.getChildren().addAll(trueRadioButton, falseRadioButton);
    }

    private HBox createTopTimerHeader() {
        timerLabel = new Label("Time: 0s");
        timerLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #333333;");

        HBox topBox = new HBox(timerLabel);
        topBox.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(topBox, Priority.ALWAYS);
        return topBox;
    }

    private void startTimer(int time) {
        stopTimer();
        timeLeft = time;
        if (timerLabel != null) {
            timerLabel.setText("Time left: " + timeLeft);
        }


        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            timeLeft--;
            if (timerLabel != null) {
                timerLabel.setText("Time left: " + timeLeft);
            }
            if (timeLeft <= 0) {
                loadNextQuestion();
            }
        });

        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }
}
