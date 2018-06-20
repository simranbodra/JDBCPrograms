package com.bridgelabz.controller;

import com.bridgelabz.serviceImplementation.UserServiceImplementation;
import com.bridgelabz.utility.Utility;

public class Controller {

	public static void main(String[] args) {
		
		UserServiceImplementation service = new UserServiceImplementation();
		int choice = 0;

		while(choice <=3) {
			System.out.println("Hello..");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.println("Enter your choice:-");
			choice = Utility.integerInput();
			Utility.stringInput();
			switch(choice) {
			case 1:
				System.out.println("Enter emial Id");
				String emailId = Utility.stringInput();
				service.register(emailId);
				break;
				
			case 2:
				System.out.println("Enter email id:-");
				String email = Utility.stringInput();
				System.out.println("Enter password:-");
				String password = Utility.stringInput();
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
