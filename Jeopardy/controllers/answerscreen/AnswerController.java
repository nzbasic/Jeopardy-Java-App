package jeopardy.controllers.answerscreen;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jeopardy.controllers.SceneController;
import jeopardy.controllers.menuscreen.MenuController;
import jeopardy.controllers.questionsscreen.QuestionController;

public class AnswerController {

    @FXML
    public Label answerShower;

    @FXML
    public Label correctIncorrect;

    @FXML
    public void menu(ActionEvent event) throws IOException {
        SceneController.generateScene(MenuController.class.getResource("Menu.fxml"));
    }
    
    @FXML 
    public void questions(ActionEvent event) throws IOException {
        QuestionController controller = new QuestionController();
        controller.initialize();
    }
    
}