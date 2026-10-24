package com.example.javaquiz744972.entities;

import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;

public class QuestionView {
    private ToggleGroup toggleGroup;
    private Node UI;

    public QuestionView(ToggleGroup toggleGroup, Node UI) {
        this.toggleGroup = toggleGroup;
        this.UI = UI;
    }


    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }

    public Node getUI() {
        return UI;
    }

    public void setUI(Node UI) {
        this.UI = UI;
    }
}
