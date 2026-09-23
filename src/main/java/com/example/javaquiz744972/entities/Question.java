package com.example.javaquiz744972.entities;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
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
}
