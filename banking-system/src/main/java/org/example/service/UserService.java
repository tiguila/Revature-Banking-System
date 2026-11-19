package org.example.service;

import org.example.Repository.UserDAO;
import org.example.model.User;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UserService {

    private UserDAO userDAO;
    private Scanner scanner;

    public UserService() {
        this.userDAO = new UserDAO();
        this.scanner = new Scanner(System.in);
    }


    public User login() {
        System.out.println("===== Log-in ======");

        System.out.println("Please enter username:");
        String username = scanner.next();
        System.out.println();


        System.out.println("Please enter password:");
        String password = scanner.next();
        System.out.println();

        return userDAO.login(username, password);
    }



//    getUserByUsername
    public User getUserByUsername() {
        System.out.println("===== get user by username ======");

        System.out.println("Please enter username:");
        String username = scanner.next();
        System.out.println();
        return userDAO.getUserByUsername(username);
    }


    public User getUserByUserId() {
        System.out.println("===== get user by userId ======");

        System.out.println("Please enter user ID:");
        int userId = scanner.nextInt();
        System.out.println();
        return userDAO.getUserById(userId);
    }




    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }



    public User createUser() {

        System.out.println("===== Create User ======");

        System.out.println("Select user type:");
        System.out.println("1 --> Customer");
        System.out.println("2 --> Employee");

        int userTypeInput = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        String userType;

        if (userTypeInput == 1) {
            userType = "customer";
        }
        else {
            userType = "employee";
        }

        System.out.println("Please enter username:");
        String username = scanner.nextLine();

        System.out.println("Please enter password:");
        String password = scanner.nextLine();

        // Create User object
        User user = new User(userType, username, password);

        // Save user to database
        userDAO.createUser(user);
        return userDAO.login(username, password);
    }



}
