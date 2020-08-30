package jeopardy.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jeopardy.Jeopardy;
import jeopardy.Question;
import javafx.scene.Node;

public class InputController {

    @FXML
    public TextField textField;

    @FXML
    public Label questionText;

    @FXML
    public void initialize() {
        // set the question text
    }
    

    @FXML 
    public void submitAnswer(ActionEvent event) throws IOException {
        Scene scene = ((Node)event.getSource()).getScene();
        TextField txt = (TextField)scene.lookup("#textField");
        CharSequence chars = txt.getCharacters();

        if (chars.toString().toLowerCase().trim().equals(Jeopardy.getActiveQuestion().getAnswer().toLowerCase().trim())) {
            Jeopardy.getActiveQuestion().done(true);
        } else {
            Jeopardy.getActiveQuestion().done(false);
        }
        scene = SceneController.generateScene("./AnswerScreen.fxml");
        Label displayCorrectIncorrect = (Label)scene.lookup("#correctIncorrect");
        Label displayAnswerShower = (Label)scene.lookup("#answerShower");

        if (Jeopardy.getActiveQuestion().wasCorrect()) {
            displayCorrectIncorrect.setText("Correct!");
        } else {
            displayCorrectIncorrect.setText("Incorrect");
        }

        displayAnswerShower.setText("The correct answer was: " + Jeopardy.getActiveQuestion().getAnswer());

    }
    
}