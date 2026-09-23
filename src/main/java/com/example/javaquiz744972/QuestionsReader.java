package com.example.javaquiz744972;

import com.example.javaquiz744972.entities.Page;
import tools.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class QuestionsReader {
    private final ObjectMapper mapper = new ObjectMapper();
    private Logger logger = Logger.getLogger(QuestionsReader.class.getName());
    public void ReadQuestions() throws FileNotFoundException {
        FileDialog fd = new FileDialog(new JFrame(), "Select Questions", FileDialog.LOAD);
        fd.setVisible(true);
        String file = fd.getFile();
        logger.info(file);
    }
}
