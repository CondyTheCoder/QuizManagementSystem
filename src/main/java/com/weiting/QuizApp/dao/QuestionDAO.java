package com.weiting.QuizApp.dao;

import com.weiting.QuizApp.dao.rowMapper.ChoiceRowMapper;
import com.weiting.QuizApp.dao.rowMapper.QuestionRowMapper;
import com.weiting.QuizApp.domain.Choice;
import com.weiting.QuizApp.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class QuestionDAO {

    private final DataSource dataSource; // Injected DataSource
    private final JdbcTemplate jdbcTemplate;
    private final QuestionRowMapper rowMapper;
    private final ChoiceRowMapper choiceRowMapper;
    @Autowired
    public QuestionDAO(DataSource dataSource, QuestionRowMapper rowMapper, JdbcTemplate jdbcTemplate, ChoiceRowMapper choiceRowMapper) {
        this.dataSource = dataSource;
        this.rowMapper = rowMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.choiceRowMapper = choiceRowMapper;
    }

//    public List<Question> getAllQuestionsByCategoryIdAndLimit(Long categoryId, int limit) {
//        String sql = "SELECT * FROM question WHERE category_id = ? ORDER BY RAND() LIMIT ?";
//        List<Question> questions = jdbcTemplate.query(sql, new Object[]{categoryId, limit}, new QuestionRowMapper());
//        return questions;
//    }


    public List<Question> getQuestionsByCategoryId(Integer categoryId, int limit) {
        String sql = "SELECT *  FROM question WHERE category_id = ? ORDER BY RAND() LIMIT ?";
        List<Question> questions = jdbcTemplate.query(sql, new Object[]{categoryId, limit}, rowMapper);
        for(int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            String sql2 = "SELECT * FROM choice WHERE question_id = ?";
            List<Choice> choices = jdbcTemplate.query(sql2, new Object[]{q.getQuestion_id()}, choiceRowMapper);
            q.setChoices(choices);
        }
        return questions;
    }

    public Question getQuestionById(Integer questionId) {
        String sql = "SELECT * FROM question WHERE question_id = ?";
        Question question = jdbcTemplate.queryForObject(sql, new Object[]{questionId}, rowMapper);
        String sql2 = "SELECT * FROM choice WHERE question_id = ?";
        List<Choice> choices = jdbcTemplate.query(sql2, new Object[]{questionId}, choiceRowMapper);
        question.setChoices(choices);
        return question;
    }

    public List<Question> getAllQuestions() {
        String sql = "SELECT * FROM question";
        List<Question> questions = jdbcTemplate.query(sql, rowMapper);
        for(int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            String sql2 = "SELECT * FROM choice WHERE question_id = ?";
            List<Choice> choices = jdbcTemplate.query(sql2, new Object[]{q.getQuestion_id()}, choiceRowMapper);
            q.setChoices(choices);
        }
        return questions;
    }

    public void saveQuestion(Question question) {
        String sql = "INSERT INTO question (description, category_id, is_active) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, question.getDescription(), question.getCategory_id(), question.is_active());
    }

    public void updateQuestion(Question question) {

        String sql = "UPDATE question SET description=?, category_id=?, is_active=? WHERE question_id=?";
        jdbcTemplate.update(sql, question.getDescription(), question.getCategory_id(), question.is_active(), question.getQuestion_id());
    }

    public int getNextQuestionId() {
        String sql = "SELECT MAX(question_id) FROM question";
        Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
        return (maxId != null) ? maxId + 1 : 1; // Increment the max ID by 1, or start at 1 if the table is empty
    }
}
