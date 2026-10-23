package com.example.javaquiz744972.entities;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.util.List;
public class RadioQuestion extends Question {
 private String choicesOrder;
 private List<String> choices;
 private String correctAnswer;
 private ToggleGroup toggleGroup;

    public RadioQuestion(String name, String type, String title, boolean isRequired, String choiceOrder, List<String> choices, String answer) {
        super(name, type, title, isRequired);
        this.choicesOrder = choiceOrder;
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
        titleLabel.getStyleClass().add("questionName");
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
        setAnsweredCorrectly(selectedToggle.getUserData().equals(correctAnswer));
        return isAnsweredCorrectly();
    }

    public boolean isToggleNull() {
        return toggleGroup.getSelectedToggle() == null;
    }

    public String getChoicesOrder() {
        return choicesOrder;
    }

    public void setChoicesOrder(String choicesOrder) {
        this.choicesOrder = choicesOrder;
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

    @Override
    public void validate() {
        super.validate();
        if (choicesOrder == null || choicesOrder.isEmpty()) {
            throw new IllegalArgumentException("Missing required property: 'choiceOrder'");
        }
        if (choices == null || choices.isEmpty()) {
            throw new IllegalArgumentException("Missing required property: 'choices'");
        }
        if (correctAnswer == null || correctAnswer.isEmpty()) {
            throw new IllegalArgumentException("Missing required property: 'correctAnswer'");
        }
    }
}
