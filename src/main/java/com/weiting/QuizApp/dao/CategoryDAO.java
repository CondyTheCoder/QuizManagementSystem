package com.weiting.QuizApp.dao;

import com.weiting.QuizApp.dao.rowMapper.CategoryRowMapper;
import com.weiting.QuizApp.dao.rowMapper.UserRowMapper;
import com.weiting.QuizApp.domain.Category;
import com.weiting.QuizApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CategoryDAO {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final CategoryRowMapper rowMapper;

    @Autowired
    public CategoryDAO(DataSource dataSource, JdbcTemplate jdbcTemplate, CategoryRowMapper rowMapper) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public List<Category> getAllCategories() {
        String query = "SELECT * FROM Category";

        List<Category> categories = jdbcTemplate.query(query, rowMapper);

        return categories;
    }

    public String getCategoryNameById(Integer categoryId) {
        String query = "SELECT name FROM Category WHERE category_id = ?";

        try {
            // queryForObject is used when we expect a single row/object to be returned
            String categoryName = jdbcTemplate.queryForObject(query, new Object[]{categoryId}, String.class);
            return categoryName;
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where no category is found for the given ID
            return null; // or handle it appropriately
        } catch (DataAccessException e) {
            // Handle other data access exceptions
            throw e;
        }
    }

//    public Integer getCategoryIdByStringID(String categoryFilter) {
//        String query = "SELECT category_id FROM Category WHERE category_id = ?";
//
//        try {
//            // queryForObject is used when we expect a single row/object to be returned
//            Integer categoryId = jdbcTemplate.queryForObject(query, new Object[]{categoryFilter}, Integer.class);
//            return categoryId;
//        } catch (EmptyResultDataAccessException e) {
//            // Handle the case where no category is found for the given ID
//            return null; // or handle it appropriately
//        } catch (DataAccessException e) {
//            // Handle other data access exceptions
//            throw e;
//        }
//    }
}
