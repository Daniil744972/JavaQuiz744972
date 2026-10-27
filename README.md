# Java Quiz Game

## Overview

Java Quiz Game is a Java application that allows users to complete quizzes loaded from JSON files. It supports different question types, tracks the player's score, and saves quiz results to a JSON file.

The application uses JavaFX for its graphical user interface and follows the Model–View–Controller (MVC) architectural pattern.

## Features

- Load quizzes from JSON files.
- Support Boolean (True/False) and multiple-choice questions.
- Select answers using radio buttons.
- Calculate the player's score based on correct answers.
- Navigate through quiz questions.
- Save quiz results to a JSON file.
- Store multiple results for the same quiz.
- Display a graphical user interface built with JavaFX.

## Technologies

- Java 25
- JavaFX
- FXML
- Jackson for JSON processing
- Maven
- CSS for interface styling

## How to Use

1. Start the application.
2. Select a quiz JSON file using the file chooser.
3. Enter the player's name.
4. Read each question and select an answer.
5. Submit the answer to continue through the quiz.
6. Complete the quiz to finish the session.
7. The result is saved in a JSON file named `<quizId>-results.json` in the selected quiz directory.

## Project Architecture

The application uses the MVC architectural pattern.

### Model

The model contains the quiz data and question-related logic.

Examples include:

- `Quiz`
- `Page`
- `Question`
- `BooleanQuestion`
- `RadioQuestion`

Question classes store question information and determine whether an answer is correct.

### View

The view contains the JavaFX interface, including FXML-based screens and dynamically generated question controls.

`QuestionViewFactory` creates the appropriate question view based on the question type.

### Controller

The controllers handle user interactions and coordinate between the view and `GameManager`.

### GameManager

`GameManager` coordinates the quiz session, including the current question, score, navigation, and results.

It is implemented as a Singleton so the application can access a shared game manager instance.

## Design Patterns

### MVC (Model–View–Controller)

Separates quiz data and answer-checking logic from the user interface and user interaction handling.

### Factory

`QuestionViewFactory` creates the appropriate JavaFX view for supported question types, including Boolean and multiple-choice questions.

### Singleton

`GameManager` uses a private constructor and a static `getInstance()` method to provide a shared instance.

### Observer

`GameManager` maintains a collection of `GameObserver` objects and provides methods to register and remove observers. Observers can receive notifications for game reset events.

## Results Storage

Quiz results are stored separately from the original quiz file.

The results file uses the quiz ID in its filename and contains a `results` array. Each entry stores information about a completed attempt, such as the player's name, number of questions, score, and date.

When a results file already exists, new results are appended to the existing collection.

## Error Handling

The application should handle common problems such as missing or invalid quiz files and invalid quiz data. Unsupported question types should be reported clearly rather than causing an unexpected application crash.

## Design Decisions

- JSON is used to store quiz definitions and quiz results.
- JavaFX is used to build the graphical user interface.
- `QuestionViewFactory` centralizes the creation of question controls.
- `GameManager` coordinates the active quiz session.
- Results are stored separately from quiz definitions so the original quiz data is not overwritten by quiz attempts.

