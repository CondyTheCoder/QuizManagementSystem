package com.weiting.QuizApp.service;

import com.weiting.QuizApp.dao.CategoryDAO;
import com.weiting.QuizApp.dao.QuizDAO;
import com.weiting.QuizApp.dao.QuizDetailDAO;
import com.weiting.QuizApp.domain.Quiz;
import com.weiting.QuizApp.domain.QuizDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    private final QuizDAO quizDao;
    private final QuizDetailDAO quizDetailDAO;
    private final CategoryDAO categoryDAO;

    @Autowired
    public QuizService(QuizDAO quizDao, QuizDetailDAO quizDetailDAO, CategoryDAO categoryDAO) {
        this.quizDao = quizDao;
        this.quizDetailDAO = quizDetailDAO;
        this.categoryDAO = categoryDAO;

    }

    public int createNewQuiz(int user_id, int category_id, String name, String time_start, String time_end, String result,String date) {

        return quizDao.createNewQuiz(user_id, category_id, name, time_start, time_end, result, date);
    }

    public List<Quiz> getAllQuizzes() {
        return quizDao.getAllQuizzes();
    }

    public List<Quiz> getAllQuizzesSortedByTime() {
        return quizDao.getAllQuizzesSortedByTime();
    }
    public List<Quiz> getAllQuizzesByUserId(int user_id) {
        return quizDao.getAllQuizzesByUserId(user_id);
    }
    public Quiz getQuizById(int quiz_id) {
        return quizDao.getQuizById(quiz_id);
    }

    public void createNewQuizDetail(Integer quiz_id, Integer question1, Integer question2, Integer question3, Integer question4, Integer question5,
                                    String choice1, String choice2,   String choice3,   String choice4,   String choice5,
                                    String correct1, String correct2,   String correct3,   String correct4,   String correct5, String correctChoice1,
                                    String correctChoice2, String correctChoice3, String correctChoice4, String correctChoice5) {
        quizDetailDAO.createNewQuizDetail(quiz_id, question1, question2, question3, question4, question5, choice1, choice2, choice3, choice4, choice5,
                correct1, correct2, correct3, correct4, correct5, correctChoice1, correctChoice2, correctChoice3, correctChoice4, correctChoice5);
    }
    public QuizDetail getQuizDetailById(int quiz_id) {
        return quizDetailDAO.getQuizDetailById(quiz_id);
    }

    public List<Quiz> getQuizzesByCategory(String categoryFilter) {

        return quizDao.getQuizzesByCategory(Integer.parseInt(categoryFilter));
    }

    public List<Quiz> getQuizzesByUser(String userFilter) {
        Integer id = Integer.parseInt(userFilter);
        return quizDao.getAllQuizzesByUserId(id);
    }
}
