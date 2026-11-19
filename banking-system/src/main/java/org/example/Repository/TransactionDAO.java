package org.example.Repository;

import org.example.controller.TransactionController;
import org.example.database.DBConnectionFactory;
import org.example.model.Account;
import org.example.model.Transaction;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    TransactionController transactionController;
    AccountDAO accountDAO;

    public TransactionDAO() {
        this.accountDAO = new AccountDAO();
    }

    public boolean depositFunds(Account account, double amount) {

        String updateSQL = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        String insertSQL = "INSERT INTO transactions (account_id, transaction_type, amount) VALUES (?, 'deposit', ?)";
        try (Connection conn = DBConnectionFactory.getConnection()) {

            conn.setAutoCommit(false);

            try {
                // Update balance
                try (PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
                    stmt.setDouble(1, amount);
                    stmt.setInt(2, account.getAccountId());

                    if (stmt.executeUpdate() == 0) {
                        conn.rollback();
                        return false;
                    }
                }

                // Insert transaction record
                try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                    stmt.setInt(1, account.getAccountId());
                    stmt.setDouble(2, amount);
                    stmt.executeUpdate();
                }

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println("Deposit error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }






    public void insertTransaction(Transaction transaction) {

        String sql = "INSERT INTO transactions (account_id, transaction_type, amount) VALUES (?, ?, ?)";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transaction.getAccountId());
            stmt.setString(2, transaction.getTransactionType());
            stmt.setDouble(3, transaction.getAmount());

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Transaction inserted successfully!");
            } else {
                System.out.println("Failed to insert transaction.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting transaction: " + e.getMessage());
            e.printStackTrace();
        }
    }





    public boolean withdrawFunds(Account account, double amount) {

        String sql = "UPDATE accounts SET balance = balance - ? " +
                "WHERE account_id = ? AND balance >= ?";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, amount);
            stmt.setInt(2, account.getAccountId());
            stmt.setDouble(3, amount);

            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Error withdrawing funds: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }







    public Account getAccountByUserId(int userId) {

        String sql = "SELECT * FROM accounts WHERE user_id = ?";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Account account = new Account();

                    account.setAccountId(rs.getInt("account_id"));
                    account.setUserId(rs.getInt("user_id"));
                    account.setAccountNumber(rs.getInt("account_number"));
                    account.setBalance(rs.getDouble("balance"));
                    account.setStatus(rs.getString("status"));

                    return account;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving account: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }






}
