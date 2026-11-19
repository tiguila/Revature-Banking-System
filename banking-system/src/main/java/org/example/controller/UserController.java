package org.example.controller;

import org.example.Repository.UserDAO;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

public class UserController {

    private UserService userService;

    public UserController() {

//        this.userDAO = new UserDAO();
        this.userService = new UserService();
    }

    public void createUser() {
        userService.createUser();
    }
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User getUserByUsername() {
        return userService.getUserByUsername();
    }

    public User getUserById() {
        return userService.getUserByUserId();
    }

    public User login() {
        return userService.login();
    }


}
