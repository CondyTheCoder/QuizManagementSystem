package com.weiting.QuizApp.controller;

import com.weiting.QuizApp.domain.User;
import com.weiting.QuizApp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        model.addAttribute("onLoginPage", true);
        System.out.println("In getLogin");
//         redirect to /quiz if user is already logged in
        if (session != null && session.getAttribute("user") != null) {

            return "redirect:/home";
        }

        return "login";
    }

    // validate that we are always getting a new session after login
    @PostMapping("/login")
    public String postLogin(@RequestParam String email,
                            @RequestParam String password,
                            HttpServletRequest request) {
        System.out.println("In postLogin");
        Optional<User> possibleUser = loginService.validateLogin(email, password);

        if(possibleUser.isPresent()) {
            HttpSession oldSession = request.getSession(false);
            // invalidate old session if it exists

            User user = possibleUser.get();

            if (oldSession != null) oldSession.invalidate();

            // generate new session
            HttpSession newSession = request.getSession(true);

            // store user details in session
            newSession.setAttribute("user", possibleUser.get());
            newSession.setAttribute("firstname", possibleUser.get().getFirstname());
            newSession.setAttribute("lastname", possibleUser.get().getLastname());

            if(user.is_admin()) {
                return "redirect:/admin-home";
            } else {
                return "redirect:/home";
            }
        } else { // if user details are invalid
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession oldSession = request.getSession(false);
        // invalidate old session if it exists
        if(oldSession != null) oldSession.invalidate();
        return "login";
    }
}
