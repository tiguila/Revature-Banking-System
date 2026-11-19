package org.example.service;

import org.example.Repository.AccountDAO;
import org.example.Repository.TransactionDAO;
import org.example.Repository.UserDAO;
import org.example.controller.TransactionController;
import org.example.model.Account;
import org.example.model.User;

import java.util.Random;
import java.util.Scanner;

public class TransactionService {

    private Scanner scanner;

    UserDAO userDAO;
    AccountDAO accountDAO;
    TransactionDAO transactionDAO;

    public TransactionService(){
        this.scanner = new Scanner(System.in);
        this.userDAO = new UserDAO();
        this.accountDAO = new AccountDAO();
        this.transactionDAO = new TransactionDAO();
    }








    public Account createAccount() {
        System.out.println("===== CREATING Account======");
        System.out.println("Please enter username:");
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


//    get user aka acccount holder by id
        User accountHolder = userDAO.getUserByUsername(username);

        while(accountHolder == null){
            System.out.println("Wrong input. Try again");

            System.out.println("Please enter username:");
            username = scanner.next();
            accountHolder = userDAO.getUserByUsername(username);
            System.out.println();

            if (accountHolder !=null){
                break;
            }
        }

//        True if the given userId exists
        if (accountHolder != null) {
            return new Account(accountHolder.getUserId(), balance, status);
        }

        return null;
    }





    //completed successfully
    public Account getAccountbyUserId() {

        System.out.println("===== Get Account given User ID ======");
        System.out.println("Please enter user ID:");

        int userId = scanner.nextInt();
        return transactionDAO.getAccountByUserId(userId);

    }







    //completed successfully
    public Account withdraw(User currentUser) {

        System.out.println("===== Withdrawal ======");
        System.out.println("Please enter amount to withdraw:");

        double amountToWithdraw = scanner.nextDouble();

        // Retrieve user's account
        Account account = transactionDAO.getAccountByUserId(currentUser.getUserId());

        if (account == null) {
            System.out.println("Account not found.");
            return null;
        }

        // Validate withdrawal amount
        if (amountToWithdraw <= 0) {
            System.out.println("Unable to process invalid withdrawal amount.");
            return account;
        }

        // Process withdrawal
        boolean success = transactionDAO.withdrawFunds(account, amountToWithdraw);

        if (success) {
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Withdrawal failed. Insufficient funds or account not found.");
        }

        return account;
    }


    //completed successfully
    public Account deposit(User currentUser) {

        System.out.println("===== Deposit ======");
        System.out.println("Please enter amount to deposit:");

        double amountToDeposit = scanner.nextDouble();

        // Retrieve user's account
        Account account = transactionDAO.getAccountByUserId(currentUser.getUserId());

        if (account == null) {
            System.out.println("Account not found.");
            return null;
        }

        // Validate deposit amount
        if (amountToDeposit <= 0) {
            System.out.println("Unable to process invalid deposit amount.");
            return account;
        }

        // Process deposit
        boolean success = transactionDAO.depositFunds(account, amountToDeposit);

        if (!success) {
            System.out.println("Deposit failed.");
        }
        return account;
    }





}
