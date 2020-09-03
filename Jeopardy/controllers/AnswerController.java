package jeopardy.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AnswerController {

    @FXML
    public Label answerShower;

    @FXML
    public Label correctIncorrect;

    @FXML
    public void menu(ActionEvent event) throws IOException {
        SceneController.generateScene("jeopardy/controllers/fxml/Menu.fxml");
    }
    
    @FXML 
    public void questions(ActionEvent event) throws IOException {
        QuestionController controller = new QuestionController();
        controller.initialize();
    }
    
}