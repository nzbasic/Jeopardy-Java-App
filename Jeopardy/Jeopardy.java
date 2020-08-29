package jeopardy;

import java.io.File;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Jeopardy {
    
    public Jeopardy() {

        File tempFolder = new File("./temp");
        if (!(tempFolder.exists() && tempFolder.isDirectory())) {
            File temp = new File("./temp");
            temp.mkdir();
            File winnings = new File("./temp/winnings");
            File categories = new File("./temp/categories");
            winnings.mkdir();
            categories.mkdir();
            File money = new File("./temp/winnings/money.txt");
            try{
                money.createNewFile();
                FileWriter writer = new FileWriter(money);
                writer.write("0");
                writer.close();
            } catch(Exception e) {
                
            }
            File folder = new File("./categories");
            File[] allFiles = folder.listFiles();
            String path = "./temp/categories/";
            for(File file : allFiles) {
                try {
                    Files.copy(file.toPath(), (new File(path + file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch(Exception e) {
                    //
                }
            }
        }
    }

    public static List<Category> questions() {

        List<Category> output = new ArrayList<Category>();

        File folder = new File("./temp/categories");
        File[] allFiles = folder.listFiles();
        for(File file : allFiles) {
            Category category = new Category(file.getName());
            try {
                Scanner sc = new Scanner(file);
                while(sc.hasNextLine()) {
                    String[] data = sc.nextLine().split(",");
                    Question question = new Question(data[0], data[1], data[2], category);
                    category.add(question);
                }
            } catch(Exception e) {

            }
            output.add(category);
        }

        return output;
    }

    
    public void gameSetup() {

    }

}