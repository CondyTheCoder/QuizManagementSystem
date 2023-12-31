package com.weiting.QuizApp.controller;

import com.weiting.QuizApp.domain.Category;
import com.weiting.QuizApp.domain.Quiz;
import com.weiting.QuizApp.domain.User;
import com.weiting.QuizApp.service.CategoryService;
import com.weiting.QuizApp.service.QuizService;
import com.weiting.QuizApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuizManagementController {

    QuizService quizService;
    CategoryService categoryService;
    UserService userService;

    @Autowired
    public QuizManagementController(QuizService quizService, CategoryService categoryService, UserService userService){
        this.quizService = quizService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    // Your code here
    @GetMapping("/quiz-management")
    public String quizManagementPage(Model model){

        List<Quiz> quizzes = quizService.getAllQuizzesSortedByTime();
        List<String> categories = new ArrayList<>();
        List<User> users = new ArrayList<>();


        for(Quiz q: quizzes){
            categories.add(categoryService.getCategoryNameById(q.getCategory_id()));
            users.add(userService.getUserById(q.getUser_id()));
        }
        model.addAttribute("quizzes", quizzes);
        model.addAttribute("categories", categories);
        model.addAttribute("users", users);


        List<Category> allCategories = categoryService.getAllCategories(); // Assuming you have a method to get all categories
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("allUsers", allUsers);

        return "quiz-management";
    }


    @GetMapping("/filter")
    public String filterQuizzes(@RequestParam(required = false) String categoryFilter,
                            @RequestParam(required = false) String userFilter,
                            Model model) {

    List<Quiz> filteredQuizzes;

    if (categoryFilter != null && !categoryFilter.isEmpty()) {
        //System.out.println("Selected category: " + categoryFilter);
        filteredQuizzes = quizService.getQuizzesByCategory(categoryFilter);
    } else if (userFilter != null && !userFilter.isEmpty()) {
        //System.out.println("Selected user: " + userFilter);
        filteredQuizzes = quizService.getAllQuizzesByUserId(Integer.parseInt(userFilter));
    } else {
        filteredQuizzes = quizService.getAllQuizzesSortedByTime(); // Default to all quizzes
    }

    model.addAttribute("quizzes", filteredQuizzes);
    List<String> categories = new ArrayList<>();
    List<User> users = new ArrayList<>();

        for(Quiz q: filteredQuizzes){
            //System.out.println(q.getTime_end());
            categories.add(categoryService.getCategoryNameById(q.getCategory_id()));
            users.add(userService.getUserById(q.getUser_id()));
        }
        model.addAttribute("categories", categories);
        model.addAttribute("users", users);

        List<Category> allCategories = categoryService.getAllCategories(); // Assuming you have a method to get all categories
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("allUsers", allUsers);
    return "quiz-management";
}


}
