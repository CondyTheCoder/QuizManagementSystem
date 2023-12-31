package com.weiting.QuizApp.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Choice {
    private int choice_id;
    private int question_id;
    private String description;

    //private List<Choice> choices;
    private boolean is_correct;
}
