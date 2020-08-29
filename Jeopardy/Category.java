package jeopardy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Category {

    private List<Question> _questionList;
    private File _file;

    public Category(File file) {
        _questionList = new ArrayList<Question>();
        _file = file;
    }

    public void add(Question question) {
        _questionList.add(question);
    }

    public String getName() {
        return _file.getName();
    }

    public List<Question> getQuestions() {
        return _questionList;
    }

    public File getFile() {
        return _file;
    }

    public void remove(Question question) throws IOException {

        _questionList.remove(question);
        String path = _file.getPath();
        _file.delete();

        File file = new File(path);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        
        for (Question questions: _questionList) {
            writer.write(questions.getFormattedString());
        }
        writer.close();
    }


}