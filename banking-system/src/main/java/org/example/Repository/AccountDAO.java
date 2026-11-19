package org.example.Repository;

import org.example.database.DBConnectionFactory;
import org.example.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    public Account createAccount(int userId) {

        String sql = """
        INSERT INTO accounts (user_id)
        VALUES (?)
        returning account_id, user_id, account_number, balance, status;
        """;

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
            System.out.println("Error creating account: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    //completed successfully
    public Account updateAccountStatus(int accountId, String newStatus) {

        String sql = "UPDATE accounts SET status = ? WHERE account_id = ?";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, accountId);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                return getAcccountByAccountId(accountId);
            }

        } catch (SQLException e) {
            System.out.println("Error updating account status: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }


    //completed successfully
    public Account getAcccountByAccountId(int accountId) {

        String sql = "SELECT * FROM accounts WHERE account_id = ?";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Account account = new Account();

                    account.setAccountId(rs.getInt("account_id"));
                    account.setUserId((rs.getInt("user_id")));
                    account.setAccountNumber(rs.getInt("account_number"));
                    account.setBalance(rs.getDouble("balance"));
                    account.setStatus(rs.getString("status"));

                    return account;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving user by username: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }



    public Account getAccountbyUserId(int userId) {

        String sql = "SELECT * FROM accounts WHERE user_id = ?";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the user ID parameter
            stmt.setInt(1, userId);

            // Execute query
            try (ResultSet rs = stmt.executeQuery()) {

                // Check if account exists
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

        // Return null if no account is found
        return null;
    }



}