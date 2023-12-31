package com.weiting.QuizApp.service;

import com.weiting.QuizApp.dao.QuestionDAO;
import com.weiting.QuizApp.domain.Choice;
import com.weiting.QuizApp.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionDAO questionDao;

    @Autowired
    public QuestionService(QuestionDAO questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getQuestionsByCategoryId(Integer categoryId) {
        // Assuming you have a method in your `questionDao` to retrieve questions by category ID
        return questionDao.getQuestionsByCategoryId(categoryId, 5);
    }

    public Question getQuestionById(Integer questionId) {
        return questionDao.getQuestionById(questionId);
    }

    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    public void updateToggledQuestion(Question question) {
        questionDao.updateQuestion(question);
    }

    public void saveQuestion(Question question) {
        questionDao.saveQuestion(question);
    }
    public int getNextQuestionId() {
        return questionDao.getNextQuestionId();
    }

    public void updateQuestion(Question question) {
        questionDao.updateQuestion(question);
    }
}
