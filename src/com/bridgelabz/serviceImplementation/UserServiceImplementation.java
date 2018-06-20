package com.bridgelabz.serviceImplementation;

import com.bridgelabz.model.User;
import com.bridgelabz.service.UserService;
import com.bridgelabz.utility.Utility;

public class UserServiceImplementation implements UserService {
	
	DatabaseAccessObject object = new DatabaseAccessObject();

	/**
	 * Function for login 
	 * @param email
	 * @param password
	 */
	@Override
	public void login(String email, String password) {
		object.getDatabaseAccess();
		User user = object.checkUserInDatabase(email);
		if(user == null) {
			System.out.println("This Email-Id is not registered");
			System.out.println("Register your Email.");
			object.removeDatabaseConnection();
			return;
		}
		else {
			user = object.userLogin(email, password);
			object.removeDatabaseConnection();
			if(user != null) {
				System.out.println();
				System.out.println("Hello " + user.getUserName() + ",");
				System.out.println("Email Id:-" + user.getEmailId());
				System.out.println("Phone Number:-" + user.getPhoneNumber());
				System.out.println();
				return;
			}
		}		
	}

	/**
	 * Function for registration
	 * @param email
	 */
	@Override
	public void register(String email) {
		object.getDatabaseAccess();
		User user = object.checkUserInDatabase(email);
		if(user != null) {
			System.out.println("You are already registered");
			System.out.println("Try logging in...");
			object.removeDatabaseConnection();
			return;
		}
		else {
			System.out.println("Enter name:-");
			String name = Utility.stringInput();
			System.out.println("Enter Id:-");
			int id = Utility.integerInput();
			System.out.println("Enter phone number:-");
			long phoneNumber = Utility.longIntegerInput();
			Utility.stringInput();
			System.out.println("Enter password:-");
			String password = Utility.stringInput();
			object.registerUser(id, name, email, password, phoneNumber);
			object.removeDatabaseConnection();
			return;
		}
	}
}
