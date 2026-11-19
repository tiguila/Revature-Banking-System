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





    public Account withdraw(User currentUser) {

        System.out.println("===== Withdraw ======");
        System.out.println("Please enter amount to withdraw:");

        double amountToWithdraw = scanner.nextDouble();

        Account account = transactionDAO.getAccountByUserId(currentUser.getUserId());

        if (account == null) {
            System.out.println("Account not found.");
            return null;
        }

        if (amountToWithdraw <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return account;
        }

        if (account.getBalance() < amountToWithdraw) {
            System.out.println("Insufficient funds.");
            return account;
        }

        // Update database balance
        boolean success = transactionDAO.withdrawFunds(account, amountToWithdraw);

        if (success) {

            // Update local account object
            account.setBalance(account.getBalance() - amountToWithdraw);

            System.out.println("Withdrawal successful!");
            System.out.println("New balance: " + account.getBalance());

        } else {
            System.out.println("Withdrawal failed.");
        }
        return account;
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


    public Account updateStatus() {
        System.out.println("Please enter account number:");
        int accountNumber = scanner.nextInt();
        System.out.println();


        Account account = accountDAO.getAcccountByAccountNumber(accountNumber);

        System.out.println("Update status:");
        System.out.println("1 --> Approved");
        System.out.println("0 --> Denied");

        int status = scanner.nextInt();
        System.out.println();

        if(status == 0) {
            account.setStatus("Denied");
        }
        else {
            account.setStatus("Approved");
        }

        accountDAO.updateAccountStatus(account);

        return account;
    }









    public User createUser() {
        System.out.println("===== CREATING User======");
        System.out.println("Please enter username:");
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




//completed inside Controller as well
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
