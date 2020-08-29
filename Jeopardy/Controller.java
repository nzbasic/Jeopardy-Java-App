package jeopardy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.FileInputStream;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Path;
import java.util.List;

import java.io.IOException;

public class Controller {

    private GridPane grid = new GridPane();

    @FXML
    public void questions(ActionEvent event) throws IOException {

        List<Category> questions = Jeopardy.questions();


        Label label = new Label("cum");
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font(18));
        label.setTextFill(Color.WHITE);

        grid.gridLinesVisibleProperty();
        grid.setPadding(new Insets(10,10,10,10));
        GridPane.setConstraints(label, 0, 1);
        grid.getChildren().addAll(label);
        grid.setStyle("-fx-background-color: #0F4C75");

        AnchorPane root = new AnchorPane(grid);
        root.setStyle("-fx-background-color: #121212");

        Scene scene2 = new Scene(root, 400, 400);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene2);
        window.show();

        


    }

    @FXML
    public void menu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
    
        String fxmlDocPath = "./Menu.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        AnchorPane root = (AnchorPane) loader.load(fxmlStream);

        Scene scene2 = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    @FXML 
    public void reset(ActionEvent event) throws IOException {
        Files.walk(Paths.get("./temp/")).map(Path::toFile).sorted((o1, o2) -> -o1.compareTo(o2)).forEach(File::delete);
        Jeopardy game = new Jeopardy();
    }


}