package org.example;

import org.example.Repository.AccountDAO;
import org.example.Repository.TransactionDAO;
import org.example.Repository.UserDAO;
import org.example.controller.TransactionController;
import org.example.database.DBConnectionFactory;
import org.example.model.Account;
import org.example.model.User;
import org.example.service.UserService;
import org.w3c.dom.UserDataHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        TransactionController transactionController = new TransactionController();
        UserDAO userDAO = new UserDAO();

        User user = userDAO.getUserById(2);
        System.out.println(transactionController.deposit(user));


    }
}
