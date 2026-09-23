package com.example.javaquiz744972.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
public class RadioQuestion extends Question {
 private String choiceOrder;
 private List<String> choices;
 private String correctAnswer;

    public RadioQuestion(String name, QuestionType type, String title, boolean isRequired, String choiceOrder, List<String> choices, String answer) {
        super(name, type, title, isRequired);
        this.choiceOrder = choiceOrder;
        this.choices = choices;
        this.correctAnswer = answer;
    }

    public RadioQuestion() {}

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
