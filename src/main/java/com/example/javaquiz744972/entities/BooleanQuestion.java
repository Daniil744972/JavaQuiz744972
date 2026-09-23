package com.example.javaquiz744972.entities;

public class BooleanQuestion extends  Question {
private String labaelTrue;
private String labaelFalse;
private boolean correctAnswer;

    public BooleanQuestion(String name, QuestionType type, String title, boolean isRequired, String labaelTrue, String labaelFalse, boolean correctAnswer) {
        super(name, type, title, isRequired);
        this.labaelTrue = labaelTrue;
        this.labaelFalse = labaelFalse;
        this.correctAnswer = correctAnswer;
    }
}


