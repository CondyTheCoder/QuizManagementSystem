package com.weiting.QuizApp.dao;

import com.weiting.QuizApp.dao.rowMapper.QuizRowMapper;
import com.weiting.QuizApp.dao.rowMapper.UserRowMapper;
import com.weiting.QuizApp.domain.Quiz;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.sql.*;
import java.util.List;

@Repository
public class QuizDAO {
    private final DataSource dataSource; // Injected DataSource
    private final JdbcTemplate jdbcTemplate;
    private final QuizRowMapper rowMapper;

    public QuizDAO(DataSource dataSource, JdbcTemplate jdbcTemplate, QuizRowMapper rowMapper) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public int createNewQuiz(int userId, int categoryId, String name,
                              String timeStart, String timeEnd, String result, String date) {
        String sql = "INSERT INTO Quiz (user_id, category_id, name, time_start, time_end, result, date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        int newQuizId = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the insert statement
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, categoryId);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, timeStart);
            preparedStatement.setString(5, timeEnd);
            preparedStatement.setString(6, result);
            preparedStatement.setString(7, date);

            // Execute the insert statement and get the generated quiz_id
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        newQuizId = generatedKeys.getInt(1);

                    } else {
                        System.out.println("Creating quiz failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Error creating the quiz");
        }
        return newQuizId;
    }

    public Quiz getQuizById(int quiz_id) {
        String sql = "SELECT * FROM Quiz WHERE quiz_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, quiz_id);
    }

    public List<Quiz> getAllQuizzes() {
        String sql = "SELECT * FROM Quiz";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Quiz> getAllQuizzesByUserId(int userId) {
        String sql = "SELECT * FROM Quiz WHERE user_id = ?";
        return jdbcTemplate.query(sql, rowMapper, userId);
    }

    public List<Quiz> getAllQuizzesSortedByTime() {
        String sql = "SELECT * FROM Quiz ORDER BY time_start DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Quiz> getQuizzesByCategory(Integer categoryId) {
        String sql = "SELECT * FROM Quiz WHERE category_id = ?";
        return jdbcTemplate.query(sql, rowMapper, categoryId);
    }
}
