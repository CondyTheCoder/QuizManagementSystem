package com.weiting.QuizApp.controller;

import com.weiting.QuizApp.domain.ContactUsMessage;
import com.weiting.QuizApp.service.ContactUsService;
import com.weiting.QuizApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    @GetMapping("/contactus")
    public String showContactForm(Model model) {

        // You can add any additional data to the model if needed
        return "contactus";
    }
    @GetMapping("/thankyou")
    public String getThankyouPage(Model model) {

        // You can add any additional data to the model if needed
        return "thankyou";
    }
    @PostMapping("/send-message")
    public String sendMessage(
            @RequestParam String subject,
            @RequestParam String email,
            @RequestParam String message) {
        // Process the message and send it to the admin (e.g., via email)

        // Create a ContactUsMessage object
        ContactUsMessage contactUsMessage = new ContactUsMessage();
        contactUsMessage.setSubject(subject);
        contactUsMessage.setEmail(email);
        contactUsMessage.setMessage(message);

        // Save the contact us message to the database
        contactUsService.createNewContactUSMessage(contactUsMessage);

        // Redirect to a thank you page or the contact page with a success message
        return "redirect:/thankyou";
    }
}
