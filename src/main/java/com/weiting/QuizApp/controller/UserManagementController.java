package com.weiting.QuizApp.controller;

import com.weiting.QuizApp.domain.User;
import com.weiting.QuizApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserManagementController {

    UserService userService;

    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    // Your code here

    @GetMapping("/user-management")
    public String userManagementPage(Model model) {
        // Your logic here
        model.addAttribute("users", userService.getAllUsers());

        return "user-management"; // The name of your JSP file for the user management page
    }

    @PostMapping("/toggleUserStatus")
    public String toggleUserStatus(@RequestParam("userId") Integer userId,
                                   @RequestParam("action") String action) {
        User user = userService.getUserById(userId); // Retrieve the user based on the provided ID
        if (user != null) {
            if ("Activate".equals(action)) {
                user.set_active(true); // Activate the user
            } else if ("Suspend".equals(action)) {
                user.set_active(false); // Suspend the user
            }
            userService.updateUser(user); // Update the user in the database
        }

        return "redirect:/user-management"; // Redirect back to the user management page
    }

}
