package org.example.service;

import org.example.Repository.AccountDAO;
import org.example.model.Account;

import java.util.Scanner;

public class AccountService {
    AccountDAO accountDAO;
    Scanner scanner;

    public AccountService(){
        this.accountDAO = new AccountDAO();
        this.scanner = new Scanner(System.in);
    }

    //completed successfully
    public Account updateAccountStatus() {
        System.out.println("===== Update Account status ======");

        System.out.println("Please enter account ID:");
        int accountId = scanner.nextInt();

        System.out.println();

        System.out.println("Please enter new account status");
        System.out.println("1--> approved\n2--> denied");
        int statusInput = scanner.nextInt();

        String status = "";
        if(statusInput == 1) {
            status = "approved";
        }
        else {
            status = "denied";
        }
        return accountDAO.updateAccountStatus(accountId, status);
    }


    //completed successfully
    public Account getAcccountByAccountId() {
        System.out.println("===== Get Account by Account ID======");
        System.out.println("Please enter account ID:");
        int accountId = scanner.nextInt();
        System.out.println();

        Account account = accountDAO.getAcccountByAccountId(accountId);
        if(account == null) {
            System.out.println("Account not found");
            return null;
        }
        return account;
    }




}
