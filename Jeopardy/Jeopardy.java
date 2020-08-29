package jeopardy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            File money = new File("./temp/winnings/money.txt")
        }




        loadCategories();
    }


    
    public void gameSetup() {

    }


    private void loadCategories() {





        // load all the txt file categories and convert them to csv, save in temp folder. 

        List<List<String>> categories = new ArrayList<List<String>>();

        File folder = new File("./categories");
        File[] allFiles = folder.listFiles();

        for (File file: allFiles) {

            try {
                BufferedReader txtReader = new BufferedReader(new FileReader(file));
                while (txtReader.readLine() != null) {
                    List<String> data = Arrays.asList(txtReader.readLine().split(","));

                }
                txtReader.close();
            } catch(Exception e) {
                System.out.println("no file");
            }
        }

        
        

    }

}