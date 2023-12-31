package com.weiting.QuizApp.dao.rowMapper;

import com.weiting.QuizApp.domain.Quiz;
import com.weiting.QuizApp.domain.QuizDetail;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizDetailRowMapper implements RowMapper<QuizDetail> {
    @Override
    public QuizDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizDetail trainee = new QuizDetail();
        trainee.setQuiz_id(rs.getInt("quiz_id"));
        trainee.setQuestion1(rs.getInt("question1"));
        trainee.setQuestion2(rs.getInt("question2"));
        trainee.setQuestion3(rs.getInt("question3"));
        trainee.setQuestion4(rs.getInt("question4"));
        trainee.setQuestion5(rs.getInt("question5"));
        trainee.setChoice1(rs.getString("choice1"));
        trainee.setChoice2(rs.getString("choice2"));
        trainee.setChoice3(rs.getString("choice3"));
        trainee.setChoice4(rs.getString("choice4"));
        trainee.setChoice5(rs.getString("choice5"));
        trainee.setCorrect1(rs.getString("correct1"));
        trainee.setCorrect2(rs.getString("correct2"));
        trainee.setCorrect3(rs.getString("correct3"));
        trainee.setCorrect4(rs.getString("correct4"));
        trainee.setCorrect5(rs.getString("correct5"));
        trainee.setCorrectChoice1(rs.getString("correctChoice1"));
        trainee.setCorrectChoice2(rs.getString("correctChoice2"));
        trainee.setCorrectChoice3(rs.getString("correctChoice3"));
        trainee.setCorrectChoice4(rs.getString("correctChoice4"));
        trainee.setCorrectChoice5(rs.getString("correctChoice5"));


        return trainee;
    }
}
