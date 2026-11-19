package org.example.service;

import org.example.Repository.UserDAO;
import org.example.model.User;

import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

}
