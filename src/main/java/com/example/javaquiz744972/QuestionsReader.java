package com.example.javaquiz744972;

import com.example.javaquiz744972.entities.Page;
import com.example.javaquiz744972.entities.Quiz;
import tools.jackson.core.JacksonException;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.exc.InvalidTypeIdException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.logging.Logger;

public class QuestionsReader {
    private ObjectMapper mapper = new ObjectMapper();
    private Logger logger = Logger.getLogger(QuestionsReader.class.getName());

    public Quiz ReadQuiz() {
        FileDialog fd = new FileDialog(new JFrame(), "Select Questions", FileDialog.LOAD);
        fd.setFile("*.json");
        fd.setVisible(true);
        String file = fd.getFile();
        if (file == null || file.trim().isEmpty()) {
            return null;
        }
        File selectedFile = new File(fd.getDirectory(), fd.getFile());
        try {
            Quiz quiz = mapper.readValue(selectedFile, Quiz.class);
            for(Page page : quiz.getPages()) {
                page.getElements().getFirst().validate();
            }
            return quiz;
        } catch (InvalidTypeIdException e) {
            throw new IllegalArgumentException("Unknown question type found in JSON: '" + e.getTypeId() + "'", e);

        } catch (DatabindException e) {
            throw new IllegalArgumentException("JSON structure does not match Quiz layout.", e);

        } catch (StreamReadException e) {
            throw new IllegalArgumentException("The file is not a valid Quiz JSON file.", e);

        } catch (JacksonException e) {
            throw new IllegalArgumentException("Failed to parse Quiz file.", e);
        }

    }
}
