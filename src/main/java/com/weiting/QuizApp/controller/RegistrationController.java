package com.weiting.QuizApp.controller;

import com.weiting.QuizApp.domain.User;
import com.weiting.QuizApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        //model.addAttribute("message", "Hello, World!");
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.createNewUser(user);
        return "redirect:/login"; // Redirect to login page after successful registration
    }
}
