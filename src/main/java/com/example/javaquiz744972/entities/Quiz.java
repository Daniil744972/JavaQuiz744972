package com.example.javaquiz744972.entities;

import java.util.List;

public class Quiz {
    private String quizId;
    private String title;
    private String description;
    private List<Page> pages;

    public Quiz(String quizId, String title, String description, List<Page> pages) {
        this.quizId = quizId;
        this.title = title;
        this.description = description;
        this.pages = pages;
    }
    public Quiz() {}

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
