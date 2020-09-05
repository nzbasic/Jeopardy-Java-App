package jeopardy.controllers.menuscreen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import jeopardy.Jeopardy;
import jeopardy.controllers.questionsscreen.QuestionController;

public class MenuController {

    @FXML
    private Label winnings;
    
    @FXML 
    public void reset(ActionEvent event) throws IOException {
        Files.walk(Paths.get("./temp/")).map(Path::toFile).sorted((o1, o2) -> -o1.compareTo(o2)).forEach(File::delete);
        Jeopardy game = new Jeopardy();
        winnings.setText("$0");
        winnings.setTextFill(Color.WHITE);
        game.gameSetup();
        
    }

    @FXML 
    public void questions(ActionEvent event) throws IOException {
        QuestionController controller = new QuestionController();
        controller.initialize();
    }

    @FXML
    public void initialize() {

        int money = 0;
        File folder = new File("./temp/categories");
        File[] allFiles = folder.listFiles();
        for(File file : allFiles) {
            try {
                Scanner sc = new Scanner(file);
                while(sc.hasNextLine()) {
                    String[] data = sc.nextLine().split(",");
                    if (data[0].equals("true")) {
                        money = money + Integer.parseInt(data[1]);
                    } else if (data[0].equals("false")) {
                        money = money - Integer.parseInt(data[1]);
                    }
                }
                sc.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        if (money < 0) {
            winnings.setTextFill(Color.RED);
        } else if (money > 0) {
            winnings.setTextFill(Color.GREEN);
        } else {
            winnings.setTextFill(Color.WHITE);
        }
        winnings.setText("$" + money);
        // read the current winnings
    }

}