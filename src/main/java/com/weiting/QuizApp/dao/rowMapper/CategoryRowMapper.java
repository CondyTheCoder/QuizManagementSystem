package com.weiting.QuizApp.dao.rowMapper;

import com.weiting.QuizApp.domain.Category;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category trainee = new Category();
        trainee.setCategory_id(rs.getInt("category_id"));
        trainee.setName(rs.getString("name"));
        return trainee;
    }
}