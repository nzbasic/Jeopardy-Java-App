package jeopardy.controllers.resetscreen;

import java.io.IOException;

import javafx.fxml.FXML;
import jeopardy.controllers.SceneController;
import jeopardy.controllers.menuscreen.MenuController;

public class ResetController {
    
    @FXML
    public void menu() throws IOException {
        SceneController.generateScene(MenuController.class.getResource("Menu.fxml"));
    }


}
