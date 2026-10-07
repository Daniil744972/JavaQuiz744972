package com.example.javaquiz744972;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        InputStream stream = getClass().getResourceAsStream("/com/example/javaquiz744972/Quizavr.png");
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource("Quiz.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        stage.setTitle("Java Quiz");
        stage.setScene(scene);
        stage.getIcons().add(new Image(stream));
        stage.show();
    }

}