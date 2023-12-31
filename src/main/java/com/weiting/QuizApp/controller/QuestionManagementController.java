package com.weiting.QuizApp.controller;

import com.weiting.QuizApp.domain.Category;
import com.weiting.QuizApp.domain.Choice;
import com.weiting.QuizApp.domain.Question;
import com.weiting.QuizApp.service.CategoryService;
import com.weiting.QuizApp.service.ChoiceService;
import com.weiting.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionManagementController {


    QuestionService questionService;
    CategoryService categoryService;
    ChoiceService choiceService;
    @Autowired
    public QuestionManagementController(QuestionService questionService, CategoryService categoryService, ChoiceService choiceService){
        this.questionService = questionService;
        this.categoryService = categoryService;
        this.choiceService = choiceService;
    }

    @GetMapping("/question-management")
    public String questionManagement(Model model){
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "question-management";
    }

    @PostMapping("/toggle-question-status")
    public String toggleQuestionStatus(@RequestParam("questionId") Integer questionId,
                                       @RequestParam("action") String action) {
        Question question = questionService.getQuestionById(questionId);
        if (question != null) {
            // Assuming 'question' has a method 'setActive' to set its active status
            if ("Activate".equals(action)) {
                question.set_active(true); // Activate the question
            } else if ("Suspend".equals(action)) {
                question.set_active(false); // Suspend the question
            }

            questionService.updateToggledQuestion(question);
        }

        return "redirect:/question-management";
    }

    @GetMapping("/add-question")
    public String addQuestion(Model model){
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "add-question";
    }


    @PostMapping("/save-question")
    public String saveQuestion(@RequestParam("description") String description,
                               @RequestParam("category_id") Integer categoryId,
                               @RequestParam("correctAnswer") Integer correctAnswer,
                               @RequestParam Map<String, String> allParams,
                               @RequestParam(required = false) String isActive) {
        Question question = new Question();
        question.setDescription(description);
        question.setCategory_id(categoryId);
        question.set_active(isActive != null);
        int nextQuestionId = questionService.getNextQuestionId();
        //System.out.println(nextQuestionId);
        List<Choice> choices = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Choice choice = new Choice();
            choice.setQuestion_id(nextQuestionId);
            choice.setDescription(allParams.get("choices[" + i + "]"));
            choice.set_correct(i + 1 == correctAnswer);
            choices.add(choice);
            //System.out.println(choice.getQuestion_id());
        }

        question.setChoices(choices);

        questionService.saveQuestion(question);
        choiceService.saveChoices(choices);
        return "redirect:/question-management";
    }

    @GetMapping("/edit-question/{question_id}")
    public String editQuestion(@PathVariable Integer question_id, Model model){
        Question question = questionService.getQuestionById(question_id);
        List<Choice> choices = question.getChoices();
        model.addAttribute("question", question);
        model.addAttribute("choices", choices);
        return "edit-question";
    }

    @PostMapping("/update-question")
    public String updateQuestion(@RequestParam("questionId") Integer questionId,
                                 @RequestParam("description") String description,
                                 @RequestParam("correctAnswer") Integer correctAnswer,
                                 @RequestParam Map<String, String> allParams,
                                 @RequestParam(required = false) String isActive) {
        Question question = questionService.getQuestionById(questionId);
        question.setDescription(description);
        question.set_active(isActive != null);

        List<Choice> existingChoices = choiceService.getChoicesByQuestionId(questionId);

        // Update existing choices or create new ones
        List<Choice> updatedChoices = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Choice choice;
            if (i < existingChoices.size()) {
                // Update existing choice
                choice = existingChoices.get(i);
            } else {
                // Create new choice if not enough existing choices
                choice = new Choice();
                choice.setQuestion_id(questionId);
            }
            choice.setDescription(allParams.get("choices[" + i + "]"));
            choice.set_correct(i + 1 == correctAnswer);

            updatedChoices.add(choice);
        }

        question.setChoices(updatedChoices);
        questionService.updateQuestion(question);
        choiceService.updateChoices(updatedChoices);


        return "redirect:/question-management";
    }

}
