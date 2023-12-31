package com.weiting.QuizApp.dao.rowMapper;


import com.weiting.QuizApp.domain.Choice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class ChoiceRowMapper implements RowMapper<Choice> {


    @Override
    public Choice mapRow(ResultSet rs,  int rowNum) throws SQLException {

        Choice c = new Choice();
        c.setChoice_id(rs.getInt("choice_id"));
        c.setQuestion_id(rs.getInt("question_id"));
        c.setDescription(rs.getString("description"));
        c.set_correct(rs.getBoolean("is_correct"));
        return c;
    }
}