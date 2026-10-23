package com.example.javaquiz744972;

import com.example.javaquiz744972.entities.Page;
import com.example.javaquiz744972.entities.Question;
import com.example.javaquiz744972.entities.Quiz;
import com.example.javaquiz744972.observer.GameObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
    private static GameManager instance;
    private Quiz quiz;
    private String userName;
    private int score = 0;
    private Map<Page, Boolean> result = new HashMap<>();
    private int currentIndex = 0;
    private Page currentPage;
    private Question currentQuestion;
    private List<GameObserver> observers = new ArrayList<>();

    private GameManager() {
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void loadQuiz() {
        QuestionsReader questionsReader = new QuestionsReader();
        quiz = questionsReader.ReadQuiz();
        if(quiz == null) return;
        currentIndex = 0;
        currentPage = quiz.getPages().get(currentIndex);
        currentQuestion = currentPage.getElements().getFirst();
    }

    public void finishQuiz() {
        currentIndex = 0;
        currentPage = null;
        currentQuestion = null;
    }
    public void cleanQuiz(){
        quiz = null;
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

    public void addResult(Page page, Boolean isRight) {
        result.put(page, isRight);
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void getNextPage() {
        currentIndex++;
        if (currentIndex < quiz.getPages().size()) {
            currentPage = quiz.getPages().get(currentIndex);
            currentQuestion = currentPage.getElements().getFirst();
            return;
        }
        finishQuiz();
    }

    public void processQuestion() {
            if (currentPage != null) {
            if (currentQuestion.isToggleNull()) return;
            if (currentQuestion.isRight()) increaseScore();
            addResult(currentPage, currentQuestion.isRight());
            getNextPage();
        }
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void gameReset() {
        cleanQuiz();
        for (GameObserver observer : observers) {
            observer.onGameReset();
        }
    }
}
