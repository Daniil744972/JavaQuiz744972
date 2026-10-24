package com.example.javaquiz744972.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

@JsonTypeName("boolean")
public class BooleanQuestion extends Question {
    private String labelTrue;
    private String labelFalse;
    private boolean correctAnswer;

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



    public boolean isToggleNull(ToggleGroup toggleGroup) {
        return toggleGroup.getSelectedToggle() == null;
    }

    @Override
    public boolean isRight(ToggleGroup toggleGroup) {
        Toggle selectedToggle = toggleGroup.getSelectedToggle();
        Boolean selected = (Boolean) selectedToggle.getUserData();
        return selected == correctAnswer;
    }

    @Override
    public void validate() {
        super.validate();
        if (labelTrue == null || labelTrue == "") {
            throw new IllegalArgumentException("Missing required property: 'labelTrue'");
        }
        if (labelFalse == null || labelFalse == "") {
            throw new IllegalArgumentException("Missing required property: 'labelFalse'");
        }
    }

}


