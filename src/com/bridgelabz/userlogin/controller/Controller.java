package com.bridgelabz.userlogin.controller;

import com.bridgelabz.userlogin.service.UserServiceImpl;
import com.bridgelabz.userlogin.utility.Utility;

public class Controller {

public static void main(String[] args) {
		
		UserServiceImpl service = new UserServiceImpl(args[0]);
		int choice = 0;

		while(choice <= 3) {
			System.out.println("Hello..");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.println("Enter your choice:-");
			choice = Utility.integerInput();
			switch(choice) {
			case 1:
				System.out.println("Enter emial Id");
				String emailId = Utility.stringNextInput();
				service.register(emailId);
				break;
				
			case 2:
				System.out.println("Enter email id:-");
				String email = Utility.stringNextInput();
				System.out.println("Enter password:-");
				String password = Utility.stringNextInput();
				service.login(email, password);
				break;
				
			case 3:
				System.exit(0);
				break;
				
			default:
				System.out.println("Wrong choice");
				System.out.println("Please enter again");
				Utility.stringInput();
				
			}
		}
	}
}
