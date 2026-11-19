package org.example.controller;


import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

public class EmployeeController {

    private UserService userService;

    public EmployeeController() {
        this.userService = new UserService();
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }



}
