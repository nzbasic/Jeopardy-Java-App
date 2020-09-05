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

    public int getCategoryWinnings() {
        int total = 0;
        for (Question question : _questionList) {
            if (question.isAnswered()) {
                if (question.wasCorrect()) {
                    total = total + Integer.parseInt(question.getPrize());
                } else {
                    total = total - Integer.parseInt(question.getPrize());
                }
            }
        }
        return total;
    }

    public boolean isEmpty() {
        for (Question question: _questionList) {
            if (!question.isAnswered()) {
                return false;
            }
        }
        return true;
    }


}