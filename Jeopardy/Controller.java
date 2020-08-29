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
import javafx.scene.layout.ColumnConstraints;
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
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import java.io.IOException;

public class Controller {

    private GridPane grid = new GridPane();

    private void addPaneText(String text, int i, int j) {
        Label label = new Label(text);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font(18));
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);

        AnchorPane canvas = new AnchorPane();
        canvas.setPrefSize(200,100);
        canvas.setStyle("-fx-background-color: #0F4C75");
        canvas.getChildren().addAll(label);

        AnchorPane.setTopAnchor(label, 5.0);
        AnchorPane.setBottomAnchor(label, 5.0);
        AnchorPane.setLeftAnchor(label, 5.0);
        AnchorPane.setRightAnchor(label, 5.0);

        GridPane.setConstraints(canvas, i, j);
        grid.getColumnConstraints().add(new ColumnConstraints(100));
        grid.getChildren().addAll(canvas);
    }

    private void addButtonText(String text, int i, int j, EventHandler<ActionEvent> onClick) {
        Button button = new Button(text);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setFont(new Font(18));
        button.setTextFill(Color.YELLOW);

        button.setPrefSize(200,100);

        button.setOnAction(onClick);

        GridPane.setConstraints(button, i, j);
        grid.getColumnConstraints().add(new ColumnConstraints(100));
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

                    }
                });

                j++;
                if (j == 5) {
                    break;
                }
            }

            if (j < 5) {
                for (int k=j; k<6; k++) {
                    
                }
            }

            i++;
            if (i == 6) {
                break;
            }
        }

        if (i < 6) {
            for (int k=i; k<6; k++) {
                
                
            }
        }

        AnchorPane root = new AnchorPane(grid);
        root.setStyle("-fx-background-color: #0F4C75");
        root.setPrefSize(600,400);
        AnchorPane.setTopAnchor(grid, 10.0);
        AnchorPane.setBottomAnchor(grid, 10.0);
        AnchorPane.setLeftAnchor(grid, 10.0);
        AnchorPane.setRightAnchor(grid, 10.0);

        root.setStyle("-fx-background-color: #121212");

        Scene scene2 = new Scene(root, 400, 400);
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
    }


}