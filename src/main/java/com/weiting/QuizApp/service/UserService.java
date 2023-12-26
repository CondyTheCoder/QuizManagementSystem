package com.weiting.QuizApp.service;

import com.weiting.QuizApp.dao.UserDao;
import com.weiting.QuizApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) { this.userDao = userDao; }

    public void createNewUser(User user) {
        userDao.createNewUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(int id) {
        return userDao.getAllUsers().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(new User(-1, "invalid email", "invalid password", "null", "null"));
    }

    public Optional<User> validateLogin(String email, String password) {
        return userDao.getAllUsers().stream()
                .filter(a -> a.getEmail().equals(email)
                        && a.getPassword().equals(password))
                .findAny();
    }

}
