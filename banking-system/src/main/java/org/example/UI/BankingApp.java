package org.example.UI;

import org.example.controller.AccountController;
import org.example.controller.TransactionController;
import org.example.controller.UserController;
import org.example.model.Account;
import org.example.model.User;
import org.postgresql.util.internal.SourceStreamIOException;

import java.lang.reflect.AccessFlag;
import java.nio.channels.ShutdownChannelGroupException;
import java.util.Scanner;

public class BankingApp {

    private AccountController accountController;
    private TransactionController transactionController;
    private UserController userController;
    private Scanner scanner;
    private User currentUser;

    public BankingApp() {
        this.accountController = new AccountController();
        this.transactionController = new TransactionController();
        this.userController = new UserController();
        this.scanner = new Scanner(System.in);
    }




    public void startBankingApp() {
        startScreen();
        while(true) {


            if(currentUser.getUserType().equalsIgnoreCase("employee")) {

                System.out.println("1 --> create a new user");
                System.out.println("2 --> Display all customers");
                System.out.println("3 --> Find user by username");
                System.out.println("4 --> Find user by user ID");
                System.out.println("5. --> Change user ");
                System.out.println("6. --> Update account status");
                System.out.println("7. --> Create account");


                System.out.println("8. --> Log out ");


                int userInputChoice = scanner.nextInt();

                if(userInputChoice ==1) {
//                    Create user
                    System.out.println(userController.createUser());
                } else if (userInputChoice ==2) {
//                    Display all customers
                    for(User customer: userController.getAllUsers()) {
                        System.out.println(customer);
                    }
                } else if (userInputChoice ==3) {
//                    Find user by username
                    System.out.println(userController.getUserByUsername());
                } else if (userInputChoice ==4) {
//                    Find user by user ID
                    System.out.println(userController.getUserById());
                }
                else if (userInputChoice ==5) {
                    changeUser();
                }

                else if (userInputChoice ==6) {
                    System.out.println("Account status updated:");
                    System.out.println(accountController.updateAccountStatus());
                }
                else if (userInputChoice ==7) {
                    System.out.println("New Account:");
                    System.out.println(accountController.createAccount());
                }


                else if (userInputChoice ==8) {
                    System.out.println("Logged out successfully");
                    break;
                }


            }




            else {
                System.out.println("1 --> Start a deposit");
                System.out.println("2 --> Start a withdraw");
                System.out.println("3 --> View my account details");
                System.out.println("4 --> Change user");
                System.out.println("5 --> Log out");

                int userInputChoice = scanner.nextInt();

                if(userInputChoice ==1) {
                    transactionController.deposit(currentUser);
                } else if (userInputChoice ==2) {
                    transactionController.withdraw(currentUser);
                } else if (userInputChoice ==3) {
                    accountController.getAccountByUserId(currentUser.getUserId());
                } else if (userInputChoice ==4) {
                    changeUser();
                } else if (userInputChoice ==5) {
                    System.out.println("Logged out successfully");
                    break;
                }


            }
        }


    }



    private void startScreen() {
        currentUser = userController.login();
        System.out.println("Current user:");
        System.out.println(currentUser);
    }


    private void changeUser() {

        User newUser = userController.login();

        if (newUser != null) {
            currentUser = newUser;

            System.out.println("\nUser changed successfully!");
            System.out.println("Current user:");
            System.out.println(currentUser);
        } else {
            System.out.println("Login failed. Keeping current user.");
        }
    }


    private void logout() {

        System.out.println("Logged out successfully.");
        currentUser = null;

        // Exit the application loop
        System.exit(0);
    }

}
