package jeopardy.controllers.inputscreen;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jeopardy.Jeopardy;
import jeopardy.controllers.SceneController;
import jeopardy.controllers.answerscreen.AnswerController;
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

        scene = SceneController.generateScene(AnswerController.class.getResource("AnswerScreen.fxml"));
        Label displayCorrectIncorrect = (Label)scene.lookup("#correctIncorrect");
        Label displayAnswerShower = (Label)scene.lookup("#answerShower");

        String text = "The correct answer was: " + Jeopardy.getActiveQuestion().getAnswer();
        displayAnswerShower.setText(text);

        if (Jeopardy.getActiveQuestion().wasCorrect()) {
            displayCorrectIncorrect.setText("Correct!");
            Thread speakThread = new Thread() {
                public void run() {
                    ProcessBuilder pb = new ProcessBuilder(new String[]{"espeak", "correct"}); 
                    try {
                        pb.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            speakThread.start();

        } else {
            displayCorrectIncorrect.setText("Incorrect");
            Thread speakThread = new Thread() {
                public void run() {
                    String toSpeak = "incorrect" + text;
                    ProcessBuilder pb = new ProcessBuilder(new String[]{"espeak", toSpeak}); 
                    try {
                        pb.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            speakThread.start();
        }
    }
}