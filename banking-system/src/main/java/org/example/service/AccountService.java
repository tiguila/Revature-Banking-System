package org.example.service;

import org.example.Repository.AccountDAO;
import org.example.model.Account;

public class AccountService {
    private AccountDAO accountDAO;


    public AccountService(){
        this.accountDAO = new AccountDAO();
    }

    public Account getAcccountByAccountNumber(int accountNumber) {
        return accountDAO.getAcccountByAccountNumber(accountNumber);
    }

    public Account updateAccountStatus(Account account){
        return accountDAO.updateAccountStatus(account);
    }


}
