package com.weiting.QuizApp.domain;


import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {
    private int quiz_id;
    private int user_id;
    private int category_id;
    private String name;
    private String time_start;
    private String time_end;
    private String result;
    private String date;
}
