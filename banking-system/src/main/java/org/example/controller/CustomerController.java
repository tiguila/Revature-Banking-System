package org.example.controller;

import org.example.Repository.UserDAO;
import org.example.model.Account;
import org.example.model.Transaction;
import org.example.model.User;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class CustomerController {
    private Scanner scanner;

    UserDAO userDAO;

    public CustomerController() {
        this.scanner = new Scanner(System.in);
        this.userDAO = new UserDAO();
    }



    public Account withdraw(){
        Transaction transaction;
        int userId;
        int amount;
//        get user by userId
        User user;


//        get Account by userId

//        if account exists given userId
//        and currenet balance > withdraw ----> allow withdraw

        return null;
    }

    public Account deposit(){
        Transaction transaction;
        int userId;
        int amount;

//        get userinput for the **amount**



        //        get user by userId
//        get account given userId
//        update account balance reflecting deposit

//        return the updated Account

        return null;
    }

    public Account transfer(){
        Transaction transaction;
        int currentUserId;
        int amount;

        /*
         * get the current user data given userId
         *
         * ask for the recipients accountNumber, check if it exists
         * recipient account exists ----> check sender does not cause overdrawing
         * if so... reduce sender balance (1)
         * and increase recipient's balance (2)
         *
         * do (1) and (2) using transaction so both actions succeed or both fail
         *
         * */


        return null;
    }




    public Account createAccount() throws SQLException {
        System.out.println("===== CREATING Account======");
        System.out.println("Plese enter username:");
        String username = scanner.next();
        System.out.println();


        String status = "Pending";

//        new accounts start with a balance of ZERO
        int balance = 0;
        Random random = new Random();


        int a = 100000;
        int b = 900000;
        int accountNum = random.nextInt(b - a + 1) + b;

        String accountNumber = Integer.toString(accountNum);
        int accountId = random.nextInt(100);


        System.out.println("Please enter the account holder ID");
        int accountUserId = scanner.nextInt();
        System.out.println();


//    get user aka acccount holder by id
        User accountHolder = userDAO.getUserById(accountUserId);

//        True if the given userId exists
        if (accountHolder != null) {
            return new Account(accountUserId, accountId, accountNumber, balance, status);
        }

        return null;
    }


    public User createUser() {
        System.out.println("===== CREATING User======");
        System.out.println("Plese enter username:");
        String username = scanner.next();

        System.out.println();

        System.out.println("password:");
        String password = scanner.next();

        System.out.println("\nEnter type\n1 --> Employee\n2 --> Customer");
        String userType = null;

        int userTypeInput = scanner.nextInt();
        System.out.println();
        if (userTypeInput == 1) {
            userType = "Employee";
        }
        else {
            userType = "Customer";
        }

        Random random = new Random();
        int userId = random.nextInt(100);

        return new User(userId, userType, username, password);
    }


}
