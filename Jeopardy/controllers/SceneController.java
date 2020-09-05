package jeopardy.controllers;

import java.net.URL;

import java.io.IOException;
import java.io.InputStream;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jeopardy.controllers.inputscreen.InputController;

public class SceneController {

    public static Stage stage;
    

    public static void setScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public static Scene generateScene(URL url) throws IOException {
        
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        return scene;

    }

    public static void generateSceneWithText(InputStream stream, String question) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(stream);
        InputController controller = loader.getController();
        controller.questionText.setText(question);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}