package com.example.javaquiz744972;

import com.example.javaquiz744972.entities.Page;
import com.example.javaquiz744972.entities.Quiz;
import com.example.javaquiz744972.entities.Result;
import com.example.javaquiz744972.entities.ResultDTO;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import tools.jackson.core.JacksonException;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.exc.InvalidTypeIdException;

import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class QuestionsReaderWriter {
    private final ObjectMapper mapper = new ObjectMapper();
    private final GameManager manager = GameManager.getInstance();

    public Quiz ReadQuiz(Window owner) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select Questions");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON files", "*.json")
        );

        File selectedFile = chooser.showOpenDialog(owner);

        if (selectedFile == null) {
            return null; // User cancelled
        }

        manager.setPath(selectedFile.toPath().getParent());

        try {
            Quiz quiz = mapper.readValue(selectedFile, Quiz.class);

            for (Page page : quiz.getPages()) {
                page.getElements().getFirst().validate();
            }

            return quiz;
        } catch (InvalidTypeIdException e) {
            throw new IllegalArgumentException(
                    "Unknown question type found in JSON: '" + e.getTypeId() + "'", e
            );
        } catch (DatabindException | NullPointerException e) {
            throw new IllegalArgumentException(
                    "JSON structure does not match Quiz layout.", e
            );
        } catch (StreamReadException e) {
            throw new IllegalArgumentException(
                    "The file is not a valid Quiz JSON file.", e
            );
        } catch (JacksonException e) {
            throw new IllegalArgumentException("Failed to parse Quiz file.", e);
        }
    }


    public void WriteQuiz() {
        if (manager.getQuiz() == null) {
            throw new IllegalStateException("Quiz has not been set");
        }
        Result newResult = new Result(manager.getUserName(), manager.getQuiz().getPages().size(),
                manager.getScore(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));


        try {
            Path file = manager.getPath().resolve(manager.getQuiz().getQuizId() + "-results.json");
            ResultDTO dto;

            if (Files.exists(file)) {
                dto = mapper.readValue(file.toFile(), ResultDTO.class);
                List<Result> results = new ArrayList<>(dto.results());
                results.add(newResult);

                dto = new ResultDTO(
                        manager.getQuiz().getQuizId(),
                        manager.getQuiz().getTitle(),
                        results
                );
            } else {
                dto = new ResultDTO(
                        manager.getQuiz().getQuizId(),
                        manager.getQuiz().getTitle(),
                        new ArrayList<>(List.of(newResult)));
            }

            mapper.writeValue(file.toFile(), dto);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to write Quiz file.");
        }
    }
}
