package com.example.javaquiz744972.entities;

public class Page {
    private int timeLimit;
    private Question question;

    public Page(int timeLimit, Question question) {
        this.timeLimit = timeLimit;
        this.question = question;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
