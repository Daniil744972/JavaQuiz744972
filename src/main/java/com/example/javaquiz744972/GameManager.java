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
    private Map<Page, Boolean> result = new Map<Page, Boolean>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public Boolean get(Object key) {
            return null;
        }

        @Override
        public Boolean put(Page key, Boolean value) {
            return null;
        }

        @Override
        public Boolean remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends Page, ? extends Boolean> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<Page> keySet() {
            return Set.of();
        }

        @Override
        public Collection<Boolean> values() {
            return List.of();
        }

        @Override
        public Set<Entry<Page, Boolean>> entrySet() {
            return Set.of();
        }
    };

    private GameManager(){}

    public static GameManager getInstance(){
    if(instance == null){
            instance = new GameManager();
    }
    return instance;
    }

    public static void setInstance(GameManager instance) {
        GameManager.instance = instance;
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
