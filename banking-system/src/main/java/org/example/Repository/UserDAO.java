package org.example.Repository;

import org.example.database.DBConnectionFactory;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {



    //    given a uusername and password, given both are correct return  user info
    public User login(String username, String password) throws SQLException {

        String sql = "select * FROM users WHERE username = ? AND password = ?";

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
        }

        return null;
    }



    public User getUserById(int userId) throws SQLException {

        String sql = "select * FROM users WHERE user_id = ?";

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
        }

        return null;
    }


}

