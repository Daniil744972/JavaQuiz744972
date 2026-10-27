package com.example.javaquiz744972;

import com.example.javaquiz744972.entities.Page;
import com.example.javaquiz744972.entities.Question;
import com.example.javaquiz744972.entities.Quiz;
import com.example.javaquiz744972.observer.GameObserver;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Window;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {
    private static GameManager instance;
    private Quiz quiz;
    private String userName;
    private int score = 0;
    private final Map<Page, Boolean> result = new HashMap<>();
    private int currentIndex = 0;
    private Page currentPage;
    private Question currentQuestion;
    private final List<GameObserver> observers = new ArrayList<>();
    private Path path;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

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

    public void loadQuiz(Window owner) {
        QuestionsReaderWriter reader = new QuestionsReaderWriter();
        quiz = reader.ReadQuiz(owner);
        if (quiz == null) return;
        currentIndex = 0;
        currentPage = quiz.getPages().get(currentIndex);
        currentQuestion = currentPage.getElements().getFirst();
    }

    private void writeQuiz() throws IOException {
        QuestionsReaderWriter writer = new QuestionsReaderWriter();
        writer.WriteQuiz();
    }

    public void finishQuiz() {
        try {
            writeQuiz();
            currentIndex = 0;
            currentPage = null;
            currentQuestion = null;
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to write Quiz file.", e);
        }


    }

    public void cleanQuiz() {
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

    public void processQuestion(ToggleGroup toggleGroup) {
        if (currentPage != null) {
            if (currentQuestion.isToggleNull(toggleGroup)) return;
            if (currentQuestion.isRight(toggleGroup)) increaseScore();
            addResult(currentPage, currentQuestion.isRight(toggleGroup));
            getNextPage();
        }
    }

    public void processQuestionWithNull() {
        if (currentPage != null) {
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
