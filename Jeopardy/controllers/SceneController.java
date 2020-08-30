package jeopardy.controllers;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SceneController {

    public static Stage stage;

    public static void setScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public static Scene generateScene(String path) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fxmlStream = new FileInputStream(path);
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        return scene;

    }

    public static void generateSceneWithText(String path, String question) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        FileInputStream fxmlStream = new FileInputStream(path);
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);
        Controller controller = loader.getController();
        controller.questionText.setText(question);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}