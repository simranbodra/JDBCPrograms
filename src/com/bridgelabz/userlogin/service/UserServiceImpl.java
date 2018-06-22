package com.bridgelabz.userlogin.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.bridgelabz.userlogin.databaseaccessobject.DatabaseAccessObject;
import com.bridgelabz.userlogin.factory.Factory;
import com.bridgelabz.userlogin.model.User;
import com.bridgelabz.userlogin.utility.Utility;

public class UserServiceImpl implements UserService{

	static DatabaseAccessObject dao;
	
	public UserServiceImpl(String statementType){
		this.dao = Factory.getObject(statementType);
	}
	
	/**
	 * Function for login
	 * @param emailId
	 * @param password
	 */
	
	public void login(String emailId, String password) {
		Connection connection = null;
		try {
		
			connection = dao.getDatabaseAccess();
			User user = dao.checkUserInDatabase(emailId,connection);
			if(user == null) {
				System.out.println("This Email-Id is not registered");
				System.out.println("Register your Email.");
				return;
			}
			else {
				user = dao.userLogin(emailId, password, connection);
				if(user != null) {
					System.out.println("Hello " + user.getUserName() + ",");
					System.out.println("Email Id:-" + user.getEmailId());
					System.out.println("Phone Number:-" + user.getPhoneNumber());
					return;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(connection != null) {
				try {
					connection.close();
				}
				catch(SQLException e) {
					System.out.println("Error in establishing platform");
				}
			}
		}
	}
	
	/**
	 * Function for registration
	 * @param email
	 */
	@Override
	public void register(String email) {
		Connection connection = null;
		try {
			connection = dao.getDatabaseAccess();
			User user = dao.checkUserInDatabase(email, connection);
			if(user != null) {
				System.out.println("You are already registered");
				System.out.println("Try logging in...");
				return;
			}
			else {
				Utility.stringInput();
				User newUser = new User();
				System.out.println("Enter name:-");
				newUser.setUserName(Utility.stringInput());
				System.out.println("Enter Id:-");
				newUser.setUserId(Utility.integerInput());
				System.out.println("Enter phone number:-");
				newUser.setPhoneNumber(Utility.longIntegerInput());
				Utility.stringInput();
				System.out.println("Enter password:-");
				newUser.setPassword(Utility.stringInput());
				newUser.setEmailId(email);
				System.out.println(newUser);
				dao.registerUser(newUser, connection);
				return;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(connection != null) {
				try {
					connection.close();
				}
				catch(SQLException e) {
					System.out.println("Error in establishing platform");
				}
			}
		}
	}
}
