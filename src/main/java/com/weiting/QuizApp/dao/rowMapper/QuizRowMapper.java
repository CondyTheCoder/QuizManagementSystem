package com.weiting.QuizApp.dao.rowMapper;

import com.weiting.QuizApp.domain.Quiz;
import com.weiting.QuizApp.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class QuizRowMapper implements RowMapper<Quiz> {
    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz trainee = new Quiz();
        trainee.setQuiz_id(rs.getInt("quiz_id"));
        trainee.setUser_id(rs.getInt("user_id"));
        trainee.setCategory_id(rs.getInt("category_id"));
        trainee.setName(rs.getString("name"));
        trainee.setTime_start(rs.getString("time_start"));
        trainee.setTime_end(rs.getString("time_end"));
        trainee.setResult(rs.getString("result"));
        trainee.setDate(rs.getString("date"));

        return trainee;
    }
}
