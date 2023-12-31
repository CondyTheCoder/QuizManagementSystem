package com.weiting.QuizApp.dao;

import com.weiting.QuizApp.dao.rowMapper.ChoiceRowMapper;
import com.weiting.QuizApp.domain.Choice;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ChoiceDAO {
    private final DataSource dataSource; // Injected DataSource
    private final JdbcTemplate jdbcTemplate;
    private final ChoiceRowMapper rowMapper;

    public ChoiceDAO(DataSource dataSource, JdbcTemplate jdbcTemplate, ChoiceRowMapper rowMapper) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public Choice getChoiceById(Integer selectedChoiceId) {
        String sql = "SELECT * FROM choice WHERE choice_id = ?";
        Choice choice = jdbcTemplate.queryForObject(sql, new Object[]{selectedChoiceId}, new ChoiceRowMapper());
        return choice;
    }

    public List<Choice> getChoicesByQuestionId(Integer questionId) {
        String sql = "SELECT * FROM choice WHERE question_id = ?";
        List<Choice> choices = jdbcTemplate.query(sql, new Object[]{questionId}, new ChoiceRowMapper());
        return choices;
    }

    public void saveChoice(Choice choice) {
        String sql = "INSERT INTO choice (question_id, description, is_correct) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, choice.getQuestion_id(), choice.getDescription(), choice.is_correct());
    }

    public void updateChoice(Choice choice) {
        String sql = "UPDATE choice SET question_id = ?, description = ?, is_correct = ? WHERE choice_id = ?";
        jdbcTemplate.update(sql, choice.getQuestion_id(), choice.getDescription(), choice.is_correct(), choice.getChoice_id());
    }
}
