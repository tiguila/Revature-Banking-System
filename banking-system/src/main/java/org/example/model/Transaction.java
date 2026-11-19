package org.example.model;


public class Transaction {

    private int transactionId;
    private int accountId;

    //    deposit, withdraw, transfer
    private String transactionType;

    private double amount;





    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }




    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", accountId=" + accountId +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
