package com.weiting.QuizApp.controller;

import com.weiting.QuizApp.domain.*;
import com.weiting.QuizApp.service.ChoiceService;
import com.weiting.QuizApp.service.QuestionService;

import com.weiting.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;

import java.util.*;
import java.text.SimpleDateFormat;

@Controller
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;
    private final ChoiceService choiceService;

    @Autowired
    public QuizController(QuestionService questionService, ChoiceService choiceService, QuizService quizService) {
        this.questionService = questionService;
        this.choiceService = choiceService;
        this.quizService = quizService;
    }


    @GetMapping("/quiz")
    public String getQuiz(@SessionAttribute("category_id") Integer categoryId, Model model) {

        List<Question> questions = questionService.getQuestionsByCategoryId(categoryId);
        model.addAttribute("questions", questions);
        return "quiz";
    }
    @PostMapping("/quiz")
    public String submitQuiz(
            @RequestParam Map<String, String> selectedChoiceIds,
            HttpSession session, Model model
    ) {

        //checkRight
        int score = 0;

        List<Question> questions = new ArrayList<>();
        List<Choice> correctAnswers = new ArrayList<>();
        List<String> selectedChoices = new ArrayList<>();
        List<String> correctOrNot = new ArrayList<>();
        for(Map.Entry<String, String> entry : selectedChoiceIds.entrySet()) {
            String key = entry.getKey().replace("selectedChoice_", "");
            int questionId = Integer.parseInt(key);
            questions.add(questionService.getQuestionById(questionId));
            correctAnswers.add(choiceService.getRightChoiceByQuestionId(questionId));

            String value = entry.getValue();
            selectedChoices.add(choiceService.getChoiceById(Integer.parseInt(value)));


            if(choiceService.checkRight(Integer.parseInt(value))){
                score++;
                correctOrNot.add("Correct");
            }else{
                correctOrNot.add("Incorrect");
            }
        }


//        for(String selectedChoice :selectedChoiceIds.values()){
//            if(choiceService.checkRight(Integer.parseInt(selectedChoice))) score++;
//        }

        String result = "Fail";
        if(score >=3)  result = "Pass";


        // deal with answers options  and questions


        //date
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String formattedTime = formatter.format(now);

        SimpleDateFormat formatter2 = new SimpleDateFormat("MM-dd-yyyy");
        String formattedDate = formatter2.format(now);


        User user = (User) session.getAttribute("user");
        int id = user.getId();

        //createNewQuiz
        int quiz_id = quizService.createNewQuiz(id, (Integer)session.getAttribute("category_id"), session.getAttribute("category_name").toString(),
                session.getAttribute("start_time").toString(), formattedTime, result, formattedDate);

        quizService.createNewQuizDetail(quiz_id, questions.get(0).getQuestion_id(), questions.get(1).getQuestion_id(), questions.get(2).getQuestion_id(), questions.get(3).getQuestion_id(), questions.get(4).getQuestion_id(),
                selectedChoices.get(0), selectedChoices.get(1), selectedChoices.get(2), selectedChoices.get(3), selectedChoices.get(4),
                correctOrNot.get(0), correctOrNot.get(1), correctOrNot.get(2), correctOrNot.get(3), correctOrNot.get(4),
                correctAnswers.get(0).getDescription(), correctAnswers.get(1).getDescription(), correctAnswers.get(2).getDescription(), correctAnswers.get(3).getDescription(), correctAnswers.get(4).getDescription());

        return "redirect:/quiz-result/" + quiz_id;
    }

    @GetMapping("/quiz-result/{quiz_id}")
    public String getQuizResult(@PathVariable Integer quiz_id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        QuizDetail quizDetail = quizService.getQuizDetailById(quiz_id);
        model.addAttribute("quizDetail", quizDetail);

        model.addAttribute("quiz", quizService.getQuizById(quiz_id));

        List<Question> questions = new ArrayList<>();
        questions.add(questionService.getQuestionById(quizDetail.getQuestion1()));
        questions.add(questionService.getQuestionById(quizDetail.getQuestion2()));
        questions.add(questionService.getQuestionById(quizDetail.getQuestion3()));
        questions.add(questionService.getQuestionById(quizDetail.getQuestion4()));
        questions.add(questionService.getQuestionById(quizDetail.getQuestion5()));
        // ... similarly for other questions ...

        model.addAttribute("questions", questions);

        return "quiz-result";
    }
}

//        List<Question> questions = (List<Question>) session.getAttribute("questions");
//        if (questions != null) {
//            model.addAttribute("questions", questions);
//        }
//        List<Choice> correctAnswers = (List<Choice>) session.getAttribute("correctAnswers");
//        if (correctAnswers != null) {
//            model.addAttribute("correctAnswers", correctAnswers);
//        }

//        Optional<Choice> selectedChoice = questionService.getChoiceById(selectedChoiceId);
//
//        if (selectedChoice.isPresent()) {
//            String result = questionService.checkAnswer(selectedChoice.get());
//            model.addAttribute("selectedChoiceDescription", selectedChoice.get().getDescription());
//            model.addAttribute("result", result);
//        } else {
//            model.addAttribute("result", "Invalid choice");
//        }