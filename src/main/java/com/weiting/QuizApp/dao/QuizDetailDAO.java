package com.weiting.QuizApp.dao;

import com.weiting.QuizApp.dao.rowMapper.QuizDetailRowMapper;
import com.weiting.QuizApp.dao.rowMapper.QuizRowMapper;
import com.weiting.QuizApp.domain.QuizDetail;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class QuizDetailDAO {
    private final DataSource dataSource; // Injected DataSource
    private final JdbcTemplate jdbcTemplate;
    private final QuizDetailRowMapper rowMapper;

    public QuizDetailDAO(DataSource dataSource, QuizDetailRowMapper rowMapper, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.dataSource = dataSource;
    }

    public void createNewQuizDetail(Integer quiz_id, Integer question1, Integer question2, Integer question3, Integer question4, Integer question5,
                                    String choice1, String choice2,   String choice3,   String choice4,   String choice5,
                                    String correct1, String correct2,   String correct3,   String correct4,   String correct5,
                                    String correctChoice1, String correctChoice2, String correctChoice3, String correctChoice4, String correctChoice5) {
        String sql = "INSERT INTO quizdetail (quiz_id, question1, question2, question3, question4, question5," +
                " choice1, choice2, choice3, choice4, choice5, correct1, correct2, correct3, correct4, correct5, " +
                "correctChoice1, correctChoice2, correctChoice3, correctChoice4, correctChoice5) " +
                "VALUES(?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, quiz_id, question1, question2, question3, question4, question5, choice1, choice2, choice3, choice4, choice5,
                correct1, correct2, correct3, correct4, correct5, correctChoice1, correctChoice2, correctChoice3, correctChoice4, correctChoice5);
    }

    public QuizDetail getQuizDetailById(int quizId) {
        String sql = "SELECT * FROM quizdetail WHERE quiz_id = ?";
        QuizDetail quizDetail = jdbcTemplate.queryForObject(sql, new Object[]{quizId}, rowMapper);
        return quizDetail;
    }

}
