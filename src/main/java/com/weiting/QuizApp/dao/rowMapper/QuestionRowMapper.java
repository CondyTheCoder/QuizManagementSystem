package com.weiting.QuizApp.dao.rowMapper;

import com.weiting.QuizApp.domain.Choice;
import com.weiting.QuizApp.domain.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class QuestionRowMapper implements RowMapper<Question> {


    @Override
    public Question mapRow(ResultSet rs,  int rowNum) throws SQLException {

        Question q = new Question();
        q.setQuestion_id(rs.getInt("question_id"));
        q.setCategory_id(rs.getInt("category_id"));
        q.setDescription(rs.getString("description"));
        q.set_active(rs.getBoolean("is_active"));

        return q;
    }
}