package com.example.javaquiz744972.entities;

import java.util.List;

public class Page {
    private int timeLimit;
    private List<Question> elements;

    public Page(){}

    public Page(int timeLimit, List<Question> questions) {
        this.timeLimit = timeLimit;
        this.elements = questions;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public List<Question> getElements() {
        return elements;
    }

    public void setElements(List<Question> elements) {
        this.elements = elements;
    }
}
