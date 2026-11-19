package org.example.controller;

import org.example.model.Account;
import org.example.model.User;
import org.example.service.TransactionService;


public class TransactionController {

    TransactionService transactionService;

    public TransactionController() {
        this.transactionService = new TransactionService();
    }

    public Account deposit(User currentUser) {
        return  transactionService.deposit(currentUser);
    }

    public Account withdraw(User currentUser) {
        return transactionService.withdraw(currentUser);
    }

    public Account getAccountByUserId() {
        return transactionService.getAccountbyUserId();
    }



}
