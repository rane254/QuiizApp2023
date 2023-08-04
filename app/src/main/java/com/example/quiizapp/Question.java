package com.example.quiizapp;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question;
    private List<String> answers = new ArrayList<>();
    private String rightAnswer;
    String ans;

    public Question(String question,String rightAnswer, String ... answers ) {
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.answers.add(answers[0]);
        this.answers.add(answers[1]);
        this.answers.add(answers[2]);
        this.answers.add(answers[3]);
    }


    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
    public String getAns(){
        if(rightAnswer=="A")
        {
            return answers.get(0);
        }
        else if(rightAnswer=="B")
        {
            return answers.get(1);
        }
        else if(rightAnswer=="C")
        {
            return answers.get(2);
        }
        else
        {
            return answers.get(3);
        }
    }
}
