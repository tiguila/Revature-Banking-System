package org.example.controller;


import org.example.Repository.UserDAO;
import org.example.model.User;

import java.util.Scanner;

public class LoginController {

    private Scanner scanner;
    private UserDAO userDAO;

    public LoginController(){
        this.scanner = new Scanner(System.in);
        this.userDAO = new UserDAO();
    }


    public User login(){
        System.out.println("===== LOG IN ======");
        System.out.println("Plese enter username:");
        String username = scanner.next();

        System.out.println();

        System.out.println("password:");
        String password = scanner.next();

        System.out.println();

        User result = userDAO.login(username, password);

        if(result == null) {
            return null;
        }
        return  result;
    }


}
