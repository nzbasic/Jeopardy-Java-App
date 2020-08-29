package jeopardy;

public class Question {
    
    private int _prize;
    private String _question;
    private String _answer;

    public Question(int prize, String question, String answer) {
        _prize = prize;
        _question = question;
        _answer = answer;
    }

    public int getPrize() {
        return _prize;
    }

    public String getQuestion() {
        return _question;
    }

    public String getAnswer() {
        return _answer;
    }

}