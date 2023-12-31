package com.weiting.QuizApp.controller;

import com.weiting.QuizApp.domain.Category;
import com.weiting.QuizApp.domain.Quiz;
import com.weiting.QuizApp.domain.User;
import com.weiting.QuizApp.service.HomeService;
import com.weiting.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@Controller
public class HomeController {

    HomeService homeService;

    QuizService quizService;

    @Autowired
    public HomeController(HomeService homeService, QuizService quizService){
        this.homeService = homeService;
        this.quizService = quizService;
    }

    @GetMapping("/home")
    public String showHomeCategoryButtons(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);


        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        //System.out.println(session.getAttribute("user"));
        List<Category> categories = new ArrayList<>();
        categories = homeService.getAllCategories();

        model.addAttribute("categories", categories);
        //System.out.println(model.getAttribute("categories"));
        // You can add any additional data to the model if needed
        User user = (User)session.getAttribute("user");
        List<Quiz> quizzes = quizService.getAllQuizzesByUserId(user.getId());
        model.addAttribute("quizzes", quizzes);
        return "home";
    }

    @PostMapping("/home")
    public String submitHomeCategoryButtons(@RequestParam("selectedCategoryId") Integer selectedCategoryId, HttpSession session) {


        session.setAttribute("category_id", selectedCategoryId);
        if (session.getAttribute("start_time") != null) {
            session.removeAttribute("start_time");
        }
        if (session.getAttribute("end_time") != null) {
            session.removeAttribute("end_time");
        }
        if (session.getAttribute("category_name") != null) {
            session.removeAttribute("category_name");
        }
        if (session.getAttribute("result") != null) {
            session.removeAttribute("result");
        }


        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String formattedTime = formatter.format(now);

        session.setAttribute("category_id", selectedCategoryId);
        String name = homeService.getCategoryNameById(selectedCategoryId);

        session.setAttribute("category_name", name);
        session.setAttribute("start_time",  formattedTime);
        // You can add any additional data to the model if needed
        return "redirect:/quiz";
    }
}
