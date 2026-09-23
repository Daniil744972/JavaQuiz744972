package com.example.javaquiz744972;

import com.example.javaquiz744972.entities.Page;
import com.example.javaquiz744972.entities.Quiz;
import tools.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class QuestionsReader {
    private final ObjectMapper mapper = new ObjectMapper();
    private Logger logger = Logger.getLogger(QuestionsReader.class.getName());

    public Quiz ReadQuiz() throws FileNotFoundException {
        FileDialog fd = new FileDialog(new JFrame(), "Select Questions", FileDialog.LOAD);
        fd.setFile("*.json");
        fd.setVisible(true);
        if(fd.getFile() == null && !fd.getFile().equals("")) {
            return null;
        }
        File selectedFile = new File(fd.getDirectory(), fd.getFile());
        Quiz quiz = mapper.readValue(selectedFile, Quiz.class);

        return quiz;
    }
}
