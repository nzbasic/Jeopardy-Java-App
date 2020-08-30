package jeopardy.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jeopardy.Jeopardy;

public class MenuController {

    @FXML
    private Label winnings;
    
    @FXML 
    public void reset(ActionEvent event) throws IOException {
        Files.walk(Paths.get("./temp/")).map(Path::toFile).sorted((o1, o2) -> -o1.compareTo(o2)).forEach(File::delete);
        Jeopardy game = new Jeopardy();
        game.gameSetup();
    }

    @FXML 
    public void questions(ActionEvent event) throws IOException {
        QuestionController controller = new QuestionController();
        controller.initialize();
    }

    @FXML
    public void initialize() {
        // read the current winnings
    }

}