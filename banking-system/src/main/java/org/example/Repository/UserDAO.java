package org.example.Repository;

import org.example.database.DBConnectionFactory;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {



    // Given a username and password, return user info if both are correct.
    public User login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    User user = new User();

                    user.setUserId(rs.getInt("user_id"));
                    user.setUserType(rs.getString("userType"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));

                    return user;
                }
            }

        } catch (SQLException e) {
            System.out.println("Login database error: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }





    public User getUserById(int userId) {

        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    User user = new User();

                    user.setUserId(rs.getInt("user_id"));
                    user.setUserType(rs.getString("userType"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));

                    return user;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }



    public User getUserByUsername(String username) {

        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    User user = new User();

                    user.setUserId(rs.getInt("user_id"));
                    user.setUserType(rs.getString("userType"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));

                    return user;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving user by username: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }




    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUserType(rs.getString("userType"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}

