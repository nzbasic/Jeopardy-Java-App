package jeopardy;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jeopardy.controllers.SceneController;
import jeopardy.controllers.menuscreen.MenuController;



public class Main extends Application
{
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
     
    @Override
    public void start(Stage stage) throws IOException {
        
        Jeopardy game = new Jeopardy();
        game.gameSetup();

        Parent root = FXMLLoader.load(MenuController.class.getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        SceneController.stage = stage;
        stage.setTitle("Jeopardy!");

        SceneController.setScene(scene);
        
    }
}