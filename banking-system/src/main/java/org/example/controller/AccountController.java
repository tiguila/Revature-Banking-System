package org.example.controller;

import org.example.Repository.AccountDAO;
import org.example.model.Account;
import org.example.service.AccountService;

public class AccountController {
    private AccountService accountService;

    public AccountController(){
        this.accountService = new AccountService();
    }

    public Account createAccount() {
        return accountService.createAccount();
    }
    public Account updateAccountStatus(){
        return accountService.updateAccountStatus();
    }

    public Account getAccountById(){
        return accountService.getAcccountByAccountId();
    }

    public Account getAccountByUserId(int userId){
        return accountService.getAccountByUserId(userId);
    }
}
