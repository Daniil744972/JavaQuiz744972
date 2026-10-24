package com.example.javaquiz744972;

import com.example.javaquiz744972.entities.BooleanQuestion;
import com.example.javaquiz744972.entities.Question;
import com.example.javaquiz744972.entities.QuestionView;
import com.example.javaquiz744972.entities.RadioQuestion;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class QuestionViewFactory {

    public QuestionViewFactory() {}


    public QuestionView GetQuestionView(Question question){
        if(question instanceof BooleanQuestion){
            return getBooleanView((BooleanQuestion)question);
        }
        if(question instanceof RadioQuestion){
            return getRadioView((RadioQuestion)question);
        }
        return null;
    }

    private QuestionView getBooleanView(BooleanQuestion q){ VBox container = new VBox(10);

        Label titleLabel = new Label(q.getTitle());
        titleLabel.setWrapText(true);
        titleLabel.getStyleClass().add("questionName");

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton trueRadioButton = new RadioButton(q.getLabelTrue());
        trueRadioButton.setUserData(true);
        RadioButton falseRadioButton = new RadioButton(q.getLabelFalse());
        falseRadioButton.setUserData(false);

        trueRadioButton.setToggleGroup(toggleGroup);
        falseRadioButton.setToggleGroup(toggleGroup);

        container.getChildren().addAll(titleLabel, trueRadioButton, falseRadioButton);

        return new QuestionView(toggleGroup,container);}

    private QuestionView getRadioView(RadioQuestion q){
        VBox container = new VBox(10);

        Label titleLabel = new Label(q.getTitle());
        titleLabel.setWrapText(true);
        titleLabel.getStyleClass().add("questionName");
        container.getChildren().add(titleLabel);

        ToggleGroup toggleGroup = new ToggleGroup();

        for (String choice : q.getChoices()) {
            RadioButton rb = new RadioButton(choice);
            rb.setUserData(choice);
            rb.setToggleGroup(toggleGroup);
            container.getChildren().add(rb);
        }
        return new QuestionView(toggleGroup,container);
    }

}
