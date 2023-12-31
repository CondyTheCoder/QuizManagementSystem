package com.weiting.QuizApp.dao;

import com.weiting.QuizApp.domain.ContactUsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ContactUsDAO {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactUsDAO(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveContactUsMessage(ContactUsMessage message) {
        String sql = "INSERT INTO contact (subject, message, email, time) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, message.getSubject());
            preparedStatement.setString(2, message.getMessage());
            preparedStatement.setString(3, message.getEmail());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ContactUsMessage> getAllMessages() {
        String sql = "SELECT * FROM contact";
        List<ContactUsMessage> messages = jdbcTemplate.query(sql, (resultSet, i) -> {
            ContactUsMessage message = new ContactUsMessage();
            message.setId(resultSet.getInt("contact_id"));
            message.setSubject(resultSet.getString("subject"));
            message.setMessage(resultSet.getString("message"));
            message.setEmail(resultSet.getString("email"));
            message.setTime(resultSet.getTimestamp("time"));
            return message;
        });
        return messages;
    }
}