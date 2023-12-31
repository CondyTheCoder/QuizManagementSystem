package com.weiting.QuizApp.domain;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactUsMessage {

    private int id;

    private String subject;

    private String message;

    private String email;

    private Timestamp time;

}