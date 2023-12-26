package com.weiting.QuizApp.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    private int id;
    private String description;
    private List<Choice> choices;
    private int correctChoiceId;
}
