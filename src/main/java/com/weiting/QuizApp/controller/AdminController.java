package com.weiting.QuizApp.controller;

import com.weiting.QuizApp.service.ChoiceService;
import com.weiting.QuizApp.service.ContactUsService;
import com.weiting.QuizApp.service.QuestionService;
import com.weiting.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    QuizService quizService;

    QuestionService questionService;

    ChoiceService choiceService;

    ContactUsService contactUsService;

    @Autowired
    public AdminController(QuizService quizService, QuestionService questionService, ChoiceService choiceService, ContactUsService contactUsService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.choiceService = choiceService;
        this.contactUsService = contactUsService;
    }


    @GetMapping("/admin-home")
    public String adminHomePage() {
        // Your logic here
        return "admin-home"; // The name of your JSP file for the admin home page
    }

}
