package org.example.model;

public class Account {

    private int accountId;

    private int userId; // account holder ID
    private int accountNumber;

    private double balance;

    //    approved, denied, pending
    private String status;

    public Account(int userId, int balance, String status) {
        this.userId = userId;
        this.balance = balance;
        this.status = status;
    }

    public Account(){}

    public Account(int userId, int accountId, int accountNumber, int balance, String status) {
        this.userId = userId;
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }




    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                ", accountHolderId=" + userId +
                '}';
    }







}
