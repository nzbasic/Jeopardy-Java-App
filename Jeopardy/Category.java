package jeopardy;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String _name;
    private List<Question> _questionList;

    public Category(String name) {
        _name = name;
        _questionList = new ArrayList<Question>();
    }

    public void add(Question question) {
        _questionList.add(question);
    }

}