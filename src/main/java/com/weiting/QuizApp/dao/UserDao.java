package com.weiting.QuizApp.dao;

import com.weiting.QuizApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {



//    static {
//        users = new ArrayList<>();
//        users.add(new User(1, "user1", "pass1"));
//        users.add(new User(2, "user2", "pass2"));
//        users.add(new User(3, "user3", "pass3"));
//    }
    private final DataSource dataSource; // Injected DataSource
    private JdbcTemplate jdbcTemplate;
    private UserRowMapper rowMapper;
    @Autowired
    public UserDao(DataSource dataSource, UserRowMapper rowMapper, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.dataSource = dataSource;
    }
    public void createNewUser(User user) {
        String sql = "INSERT INTO User (email, password, firstname, lastname) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Set the parameters for the PreparedStatement
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());

            // Execute the update
            preparedStatement.executeUpdate();

            // Retrieve the generated key for the user_id
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Assuming 'users' is a collection of User objects

    }


    public List<User> getAllUsers() {
            String query = "SELECT * FROM User";

            List<User> users = jdbcTemplate.query(query, rowMapper);

            return users;
    }

}
