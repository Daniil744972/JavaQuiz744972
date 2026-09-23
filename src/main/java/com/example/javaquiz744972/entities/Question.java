package com.example.javaquiz744972.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(
                value = RadioQuestion.class,
                name = "radiogroup"
        ),
        @JsonSubTypes.Type(
                value = BooleanQuestion.class,
                name = "boolean"
        )
})
public abstract class Question {
    private String name;
    private QuestionType type;
    private String title;
    private boolean isRequired;
    public Question(String name, QuestionType type, String title, boolean isRequired) {
        this.name = name;
        this.type = type;
        this.title = title;
        this.isRequired = isRequired;
    }

    public Question() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }
}
