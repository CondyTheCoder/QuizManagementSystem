package com.weiting.QuizApp.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private int id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private boolean is_active;
    private boolean is_admin;

}
