package jeopardy;

import java.io.IOException;

public class Question {

    private String _prize;
    private String _question;
    private String _answer;
    private boolean _answered = false;
    private boolean _correct;
    private Category _parent;

    public Question(String prize, String question, String answer, Category category) {
        _prize = prize;
        _question = question;
        _answer = answer;
        _parent = category;
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

    public void done(boolean correct) throws IOException {
        setAnswered();
        setCorrect(correct);
        _parent.remove(this);
    }

    public void setCorrect(boolean correct) {
        _correct = correct;
    }

    public boolean wasCorrect() {
        return _correct;
    }

    public void setAnswered() {
        _answered = true;
    }

    public Category getParent() {
        return _parent;
    }

    public boolean isAnswered() {
        return _answered;
    }

    public String getFormattedString() {
        if (_answered && _correct) {
            return "true," + this._prize + "," + this._question + "," + this._answer + "\n";
        } else if(_answered && !_correct) {
            return "false," + this._prize + "," + this._question + "," + this._answer + "\n";
        } else {
            return this._prize + "," + this._question + "," + this._answer + "\n";
        }
        
    }

}