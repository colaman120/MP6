package com.example.jasonhu.triviaapp;

import java.util.ArrayList;
import java.util.List;

public class QGroup {

    private String question;
    private String correctOption;
    private List<String> incorrectOptions;

    public QGroup(String correct) {
        correctOption = correct;
        incorrectOptions = new ArrayList<>();
    }

    public QGroup(String challenge, String correct, List<String> incorrect) {
        question = challenge;
        correctOption = correct;
        incorrectOptions = incorrect;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public String getIncorrectOption(int i) {
        return incorrectOptions.get(i);
    }

    public void add(String inCorrectOption) {
        incorrectOptions.add(inCorrectOption);
    }
}
