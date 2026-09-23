package com.example.javaquiz744972.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
@JsonTypeName("radiogroup")
public class RadioQuestion extends Question {
 private String choiceOrder;
 private List<String> choices;
 private String answer;

    public RadioQuestion(String name, QuestionType type, String title, boolean isRequired, String choiceOrder, List<String> choices, String answer) {
        super(name, type, title, isRequired);
        this.choiceOrder = choiceOrder;
        this.choices = choices;
        this.answer = answer;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
