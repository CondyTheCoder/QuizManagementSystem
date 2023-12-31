package com.weiting.QuizApp.dao;

import com.weiting.QuizApp.dao.rowMapper.UserRowMapper;
import com.weiting.QuizApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class UserDAO {

    private final DataSource dataSource; // Injected DataSource
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper;

    @Autowired
    public UserDAO(DataSource dataSource, UserRowMapper rowMapper, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.dataSource = dataSource;
    }
    public void createNewUser(User user) {
        String sql = "INSERT INTO User (email, password, firstname, lastname, is_active, is_admin ) VALUES (?, ?, ?, ?, true, false)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());

            preparedStatement.executeUpdate();

//            // Retrieve the generated key for the user_id
//            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    user.setId(generatedKeys.getInt(1));
//                }
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public List<User> getAllUsers() {
            String query = "SELECT * FROM User";

            List<User> users = jdbcTemplate.query(query, rowMapper);

            return users;
    }

    public void setUserAdmin(User user) {
        String sql = "UPDATE User SET is_admin = true WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUserActive(User user) {
        String sql = "UPDATE User SET is_active = true WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUserInactive(User user) {
        String sql = "UPDATE User SET is_active = false WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE User SET email = ?, password = ?, firstname = ?, lastname = ?, is_active = ?, is_admin = ? WHERE user_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstname());
            preparedStatement.setString(4, user.getLastname());
            preparedStatement.setBoolean(5, user.is_active());
            preparedStatement.setBoolean(6, user.is_admin());
            preparedStatement.setInt(7, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
