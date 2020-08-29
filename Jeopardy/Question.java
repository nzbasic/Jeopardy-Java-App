package jeopardy;

public class Question {
    
    private String _prize;
    private String _question;
    private String _answer;
    private boolean _answered = false;

    public Question(String prize, String question, String answer) {
        _prize = prize;
        _question = question;
        _answer = answer;
    }

    public String getPrize() {
        return _prize;
    }

    public String getQuestion() {
        return _question;
    }

    public String getAnswer() {
        return _answer;
    }

    public void done() {
        _answered = true;
    }

}