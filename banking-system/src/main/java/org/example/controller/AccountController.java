package org.example.controller;

import org.example.Repository.AccountDAO;
import org.example.model.Account;
import org.example.service.AccountService;

public class AccountController {
    private AccountService accountService;

    public Account updateAccountStatus(){
        return accountService.updateAccountStatus();
    }


    public AccountController(){
        this.accountService = new AccountService();
    }

    public Account getAccountById(){
        return accountService.getAcccountByAccountId();
    }


}
