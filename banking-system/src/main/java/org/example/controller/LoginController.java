package org.example.controller;


import org.example.model.User;

import java.util.Scanner;

public class LoginController {

    private Scanner scanner;

    public LoginController(){
        this.scanner = new Scanner(System.in);
    }


    public User login(){
        System.out.println("===== LOG IN ======");
        System.out.println("Plese enter username:");
        String username = scanner.next();

        System.out.println();

        System.out.println("password:");
        String password = scanner.next();

        System.out.println();


//
//        userId
//                userType
//        username
//                password

//        private int ;
//        private String ;
//        private String ;
//        private String ;




        return  null;
    }


}
