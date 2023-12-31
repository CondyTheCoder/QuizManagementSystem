package com.weiting.QuizApp.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizDetail {
    private int quiz_detail_id;
    private int quiz_id;
    private int question1;
    private int question2;
    private int question3;
    private int question4;
    private int question5;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;
    private String correct1;
    private String correct2;
    private String correct3;
    private String correct4;
    private String correct5;
    private String correctChoice1;
    private String correctChoice2;
    private String correctChoice3;
    private String correctChoice4;
    private String correctChoice5;
}
