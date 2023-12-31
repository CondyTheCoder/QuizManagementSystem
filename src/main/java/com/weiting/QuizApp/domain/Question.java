package com.weiting.QuizApp.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    private int question_id;
    private int category_id;
    private String description;
    private boolean is_active;
    private List<Choice> choices; // Initialize the list


    public void setChoice(List<Choice> choice) {
        choices = new ArrayList<>(choice);
    }

    // Getter for the choices list
    public List<Choice> getChoices() {
        return choices;
    }
}