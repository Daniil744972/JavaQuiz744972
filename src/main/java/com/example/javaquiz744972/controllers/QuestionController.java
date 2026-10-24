package com.example.javaquiz744972.controllers;

import com.example.javaquiz744972.GameManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.io.IOException;


public class QuestionController {

    @FXML
    private Label quizName;

    @FXML
    private Label description;
    @FXML
    private VBox questionBox;
    @FXML
    private HBox buttonBox;
    private GameManager manager = GameManager.getInstance();
    private Label timerLabel;
    private Timeline timeline;
    private int timeLeft = 0;


    public void initialize() {
        quizName.setText(manager.getQuiz().getTitle());
        description.setText(manager.getQuiz().getDescription());

    }

    @FXML
    public void nextQuestion(ActionEvent actionEvent) {
        description.setText("");
        manager.processQuestion();
        loadNextQuestion();
    }

    private void loadNextQuestion() {
        if (manager.getCurrentPage() != null) {
            renderNextQuestion();
            startTimer();
        } else {
            finishQuiz();
        }
    }

    private void renderNextQuestion() {
        questionBox.getChildren().clear();
        questionBox.getChildren().add(createTopTimerHeader());

        Node UI = manager.getCurrentQuestion().renderUI();
        questionBox.getChildren().add(UI);
    }

    private void finishQuiz() {
        stopTimer();
        questionBox.getChildren().clear();
        buttonBox.getChildren().clear();
        String htmlTemplate = manager.getQuiz().getCompletedHtml();
        String result = htmlTemplate
                .replace("{correctAnswers}", String.valueOf(manager.getScore()))
                .replace("{questionCount}", String.valueOf(manager.getQuiz().getPages().size()));

        WebView webView = new WebView();
        webView.getEngine().loadContent(result);
        webView.setPrefHeight(100);
        webView.setPageFill(javafx.scene.paint.Color.TRANSPARENT);

        Button newTestButton = new Button("New Test");
        newTestButton.getStyleClass().add("button");
        newTestButton.setOnAction(e -> {
            try {
                restartQuiz();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.getStyleClass().add("button");
        exitButton.setOnAction(e -> handleExit());

        questionBox.getChildren().addAll(webView);
        buttonBox.getChildren().addAll(newTestButton, exitButton);
        manager.cleanQuiz();
    }

    private void restartQuiz() throws IOException {
        stopTimer();
        manager.gameReset();
    }

    private void handleExit() {
        javafx.stage.Stage stage = (javafx.stage.Stage) questionBox.getScene().getWindow();
        stage.close();
    }

    private HBox createTopTimerHeader() {
        timerLabel = new Label("Time: 0s");
        timerLabel.getStyleClass().add("timer");

        HBox topBox = new HBox(timerLabel);
        topBox.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(topBox, Priority.ALWAYS);
        return topBox;
    }


    private void startTimer() {
        stopTimer();
        timeLeft = manager.getCurrentPage().getTimeLimit();
        if (timerLabel != null) {
            timerLabel.setText("Time left: " + timeLeft);
        }


        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            timeLeft--;
            if (timerLabel != null) {
                timerLabel.setText("Time left: " + timeLeft);
            }
            if (timeLeft <= 0) {
                manager.processQuestionWithNull();
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
