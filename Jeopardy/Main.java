package jeopardy;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import jeopardy.controllers.SceneController;
 
public class Main extends Application
{
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
     
    @Override
    public void start(Stage stage) throws IOException {
 
        FXMLLoader loader = new FXMLLoader();
    
        String fxmlDocPath = "./fxml/Menu.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        AnchorPane root = (AnchorPane) loader.load(fxmlStream);
        Scene scene = new Scene(root);

        SceneController.stage = stage;
        stage.setTitle("Jeopardy!");

        SceneController.setScene(scene);

        Jeopardy game = new Jeopardy();
        game.gameSetup();
    }
}