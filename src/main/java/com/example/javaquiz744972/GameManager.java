package com.example.javaquiz744972;

import com.example.javaquiz744972.entities.Page;
import com.example.javaquiz744972.entities.Question;
import com.example.javaquiz744972.entities.Quiz;

import java.util.*;

public class GameManager {
    private static GameManager instance;
    private Quiz quiz;
    private String userName;
    private int score = 0;
    private Map<Page, Boolean> result = new HashMap<>();

    private GameManager(){}

    public static GameManager getInstance(){
    if(instance == null){
            instance = new GameManager();
    }
    return instance;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    public void addResult(Page page, Boolean isRight){
        result.put(page,isRight);
    }

}
