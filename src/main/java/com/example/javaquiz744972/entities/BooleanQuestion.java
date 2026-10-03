package com.example.javaquiz744972.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

@JsonTypeName("boolean")
public class BooleanQuestion extends Question {

    private String labelTrue;
    private String labelFalse;
    private boolean correctAnswer;
    private ToggleGroup toggleGroup;

    public BooleanQuestion(String name, String type, String title, boolean isRequired, String labaelTrue, String labaelFalse, boolean correctAnswer) {
        super(name, type, title, isRequired);
        this.labelTrue = labaelTrue;
        this.labelFalse = labaelFalse;
        this.correctAnswer = correctAnswer;
    }

    public BooleanQuestion() {
    }

    public String getLabelTrue() {
        return labelTrue;
    }

    public void setLabelTrue(String labelTrue) {
        this.labelTrue = labelTrue;
    }

    public String getLabelFalse() {
        return labelFalse;
    }

    public void setLabelFalse(String labelFalse) {
        this.labelFalse = labelFalse;
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


    @Override
    public Node renderUI() {
        VBox container = new VBox(10);

        Label titleLabel = new Label(this.getTitle());
        titleLabel.setWrapText(true);
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

       toggleGroup = new ToggleGroup();

        RadioButton trueRadioButton = new RadioButton(this.getLabelTrue());
        trueRadioButton.setUserData(true);
        RadioButton falseRadioButton = new RadioButton(this.getLabelFalse());
        falseRadioButton.setUserData(false);

        trueRadioButton.setToggleGroup(toggleGroup);
        falseRadioButton.setToggleGroup(toggleGroup);

        container.getChildren().addAll(titleLabel, trueRadioButton, falseRadioButton);

        return container;
    }

    @Override
    public boolean isRight() {
        Toggle selectedToggle = toggleGroup.getSelectedToggle();
        Boolean selected = (Boolean) selectedToggle.getUserData();
        return selected == correctAnswer;
    }

}


