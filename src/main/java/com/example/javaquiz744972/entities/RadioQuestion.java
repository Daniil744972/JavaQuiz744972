package com.example.javaquiz744972.entities;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.util.List;
public class RadioQuestion extends Question {
 private String choiceOrder;
 private List<String> choices;
 private String correctAnswer;
 private ToggleGroup toggleGroup;

    public RadioQuestion(String name, String type, String title, boolean isRequired, String choiceOrder, List<String> choices, String answer) {
        super(name, type, title, isRequired);
        this.choiceOrder = choiceOrder;
        this.choices = choices;
        this.correctAnswer = answer;
    }

    public RadioQuestion() {
        toggleGroup = new ToggleGroup();
    }

    @Override
    public Node renderUI() {
        VBox container = new VBox(10);

        Label titleLabel = new Label(this.getTitle());
        titleLabel.setWrapText(true);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        container.getChildren().add(titleLabel);

        for (String choice : getChoices()) {
            RadioButton rb = new RadioButton(choice);
            rb.setUserData(choice);
            rb.setToggleGroup(toggleGroup);
            container.getChildren().add(rb);
        }
        return container;
    }

    @Override
    public boolean isRight() {
        Toggle selectedToggle = toggleGroup.getSelectedToggle();
        return selectedToggle.getUserData().equals(correctAnswer);
    }

    public boolean isToggleNull() {
        return toggleGroup.getSelectedToggle() == null;
    }

    public String getChoiceOrder() {
        return choiceOrder;
    }

    public void setChoiceOrder(String choiceOrder) {
        this.choiceOrder = choiceOrder;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}
