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

import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import java.io.IOException;

public class Controller {

    private GridPane grid = new GridPane();

    private void addPaneText(String text, int i, int j) {
        Label label = new Label(text.toUpperCase());
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font(14));
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);

        AnchorPane canvas = new AnchorPane();
        canvas.setPrefSize(200,100);
        canvas.setStyle("-fx-background-color: #0F4C75; -fx-border-color: black; -fx-border-width: 3 3 3 3;");
        canvas.getChildren().addAll(label);

        AnchorPane.setTopAnchor(label, 5.0);
        AnchorPane.setBottomAnchor(label, 5.0);
        AnchorPane.setLeftAnchor(label, 5.0);
        AnchorPane.setRightAnchor(label, 5.0);

        GridPane.setConstraints(canvas, i, j);
        grid.getChildren().addAll(canvas);
    }

    private void addButtonText(String text, int i, int j, EventHandler<ActionEvent> onClick) {
        Button button = new Button(text);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setFont(new Font(25));
        button.setTextFill(Color.YELLOW);

        button.setPrefSize(200,100);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        button.setOnAction(onClick);

        GridPane.setConstraints(button, i, j);
        grid.getChildren().addAll(button);
    }

    @FXML
    public void questions(ActionEvent event) throws IOException {

        List<Category> categories = Jeopardy.questions();

        grid.setHgap(2);
        grid.setVgap(2);
        grid.setPrefSize(600,400);
        grid.gridLinesVisibleProperty();
        grid.setPadding(new Insets(5,5,5,5));

        int i=0; 
        for (Category category: categories) {
            
            addPaneText(category.getName(), i, 0);

            List<Question> questions = category.getQuestions();

            int j=1;
            for (Question question: questions) {
            
                addButtonText(question.getPrize(), i, j, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try{ 
                            pickQuestion(question, event);
                        } catch(Exception e) {
                        }
                    }
                });
                j++;
                if (j == 6) {
                    break;
                }
            }
            if (j < 5) {
                for (int k=j; k<6; k++) {
                    addPaneText("", i, k);
                }
            }
            i++;
            if (i == 6) {
                break;
            }
        }
        if (i < 6) {
            for (int k=i; k<6; k++) {
                addPaneText("", k, 0);
                for (int l=1; l<6; l++) {
                    addPaneText("", k, l);
                }
            }
        }

        AnchorPane root = new AnchorPane(grid);
        root.setPrefSize(1000,600);
        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);

        root.setStyle("-fx-background-color: #121212");

        Scene scene2 = new Scene(root, 800, 600);
        scene2.getStylesheets().add("./questions.css");

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
        game.gameSetup();
    }

    @FXML
    private void pickQuestion(Question question, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
    
        String fxmlDocPath = "./QuestionScreen.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        AnchorPane root = (AnchorPane) loader.load(fxmlStream);

        Scene scene2 = new Scene(root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }


}