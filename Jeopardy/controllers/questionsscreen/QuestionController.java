package jeopardy.controllers.questionsscreen;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import jeopardy.Jeopardy;
import jeopardy.Question;
import jeopardy.controllers.SceneController;
import jeopardy.controllers.inputscreen.InputController;
import jeopardy.controllers.resetscreen.ResetController;
import jeopardy.Category;

public class QuestionController {

    private GridPane grid = new GridPane();

    @FXML
    public void initialize() {
        // generate the question buttons and panels

        List<Category> categories = Jeopardy.questions();

        if (checkEmpty(categories)) {
            return;
        }

        grid.setHgap(2);
        grid.setVgap(2);
        grid.setPrefSize(600, 400);
        grid.gridLinesVisibleProperty();
        grid.setPadding(new Insets(5, 5, 5, 5));

        int i = 0;
        for (Category category : categories) {

            addPaneText(category.getName(), i, 0);

            List<Question> questions = category.getQuestions();

            int j = 1;
            for (Question question : questions) {

                if (question.isAnswered()) {
                    addAnsweredPanel(question, i, j);
                } else {
                    addButtonText(question, i, j);
                }
                j++;
                if (j == 6) {
                    break;
                }
            }
            if (j < 5) {
                for (int k = j; k < 6; k++) {
                    addPaneText("", i, k);
                }
            }
            i++;
            if (i == 6) {
                break;
            }
        }
        // if (i < 6) {
        //     for (int k = i; k < 6; k++) {
        //         addPaneText("", k, 0);
        //         for (int l = 1; l < 6; l++) {
        //             addPaneText("", k, l);
        //         }
        //     }
        // }

        AnchorPane root = new AnchorPane(grid);
        root.setPrefSize(i*200, 600);
        AnchorPane.setTopAnchor(grid, 0.0);
        AnchorPane.setBottomAnchor(grid, 0.0);
        AnchorPane.setLeftAnchor(grid, 0.0);
        AnchorPane.setRightAnchor(grid, 0.0);

        root.setStyle("-fx-background-color: #121212");

        Scene scene = new Scene(root, i*200, 600);
        scene.getStylesheets().add("questions.css");

        SceneController.setScene(scene);
    }

    private void addPaneText(String text, int i, int j) {
        Label label = new Label(text.toUpperCase());
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font(14));
        label.setTextFill(Color.WHITE);
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);

        AnchorPane canvas = new AnchorPane();
        canvas.setPrefSize(200, 100);
        canvas.setStyle("-fx-background-color: #0F4C75; -fx-border-color: black; -fx-border-width: 3 3 3 3;");
        canvas.getChildren().addAll(label);

        AnchorPane.setTopAnchor(label, 5.0);
        AnchorPane.setBottomAnchor(label, 5.0);
        AnchorPane.setLeftAnchor(label, 5.0);
        AnchorPane.setRightAnchor(label, 5.0);

        GridPane.setConstraints(canvas, i, j);
        grid.getChildren().addAll(canvas);
    }

    private void addAnsweredPanel(Question question, int i, int j) {
        Label label = new Label(question.getPrize());
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(new Font(25));
        label.setTextFill(Color.YELLOW);
        label.setAlignment(Pos.CENTER);

        AnchorPane canvas = new AnchorPane();
        canvas.setPrefSize(200, 100);
        if (question.wasCorrect()) {
            canvas.setStyle("-fx-background-color: #28b463 ; -fx-border-color: black; -fx-border-width: 3 3 3 3;");
        } else {
            canvas.setStyle("-fx-background-color: #c0392b ; -fx-border-color: black; -fx-border-width: 3 3 3 3;");
        }

        canvas.getChildren().addAll(label);

        AnchorPane.setTopAnchor(label, 5.0);
        AnchorPane.setBottomAnchor(label, 5.0);
        AnchorPane.setLeftAnchor(label, 5.0);
        AnchorPane.setRightAnchor(label, 5.0);

        GridPane.setConstraints(canvas, i, j);
        grid.getChildren().addAll(canvas);
    }

    private void addButtonText(Question question, int i, int j) {
        Button button = new Button(question.getPrize());
        button.setTextAlignment(TextAlignment.CENTER);
        button.setFont(new Font(25));
        button.setTextFill(Color.YELLOW);

        button.setPrefSize(200, 100);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        button.setOnAction(e -> {
            Jeopardy.setActiveQuestion(question);

            try {
                SceneController.generateSceneWithText(InputController.class.getResourceAsStream("InputScreen.fxml"), question.getQuestion());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        GridPane.setConstraints(button, i, j);
        grid.getChildren().addAll(button);
    }

    public boolean checkEmpty(List<Category> categories) {

        for (Category category : categories) {
            if (!category.isEmpty()) {
                return false;
            }
        }

        try {
            SceneController.generateScene(ResetController.class.getResource("ResetScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}