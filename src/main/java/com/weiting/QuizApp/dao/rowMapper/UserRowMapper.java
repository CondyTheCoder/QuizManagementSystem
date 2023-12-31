package com.weiting.QuizApp.dao.rowMapper;

import com.weiting.QuizApp.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User trainee = new User();
        trainee.setId(rs.getInt("user_id"));
        trainee.setEmail(rs.getString("email"));
        trainee.setPassword(rs.getString("password"));
        trainee.setFirstname(rs.getString("firstname"));
        trainee.setLastname(rs.getString("lastname"));
        trainee.set_active(rs.getBoolean("is_active"));
        trainee.set_admin(rs.getBoolean("is_admin"));
        return trainee;
    }
}
