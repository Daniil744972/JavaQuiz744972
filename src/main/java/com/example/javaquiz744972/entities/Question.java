package com.example.javaquiz744972.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.scene.Node;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true
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
    private String type;
    private String title;
    private boolean isRequired;
    private boolean isAnsweredCorrectly;

    public Question(String name, String type, String title, boolean isRequired) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public abstract Node renderUI();

    public abstract boolean isRight();

    public abstract boolean isToggleNull();

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", isRequired=" + isRequired +
                '}';
    }

    public void validate(){
        if(name ==null||name.isEmpty()){
            throw new IllegalArgumentException("Missing required property: 'name'");
        }
        if(type ==null||type.isEmpty()){
            throw new IllegalArgumentException("Missing required property: 'type'");
        }
        if(title==null||title.isEmpty()){
            throw new IllegalArgumentException("Missing required property: 'choice'");
        }
    }

    public boolean isAnsweredCorrectly() {
        return isAnsweredCorrectly;
    }

    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        isAnsweredCorrectly = answeredCorrectly;
    }
}
