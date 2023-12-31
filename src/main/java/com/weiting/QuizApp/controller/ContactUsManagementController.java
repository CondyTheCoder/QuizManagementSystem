package com.weiting.QuizApp.controller;

import com.weiting.QuizApp.domain.ContactUsMessage;
import com.weiting.QuizApp.service.ContactUsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContactUsManagementController {
    ContactUsService contactUsService;

    public ContactUsManagementController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @GetMapping("/contact-management")
    public String contactManagement(Model model) {
        List<ContactUsMessage>  messages = contactUsService.getAllMessages();
        model.addAttribute("contactUsMessages", messages);
        return "contact-management";
    }
}
