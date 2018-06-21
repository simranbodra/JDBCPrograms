package com.bridgelabz.serviceImplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bridgelabz.model.User;
import com.bridgelabz.service.DatabaseAccessObject;

public class DatabaseAccessObjectImpl implements DatabaseAccessObject{

	Connection connection = null;
	Statement statement = null;
	ResultSet resultset = null;
	
	/**
	 * Function to establish connection with database and create platform
	 */
	public void getDatabaseAccess() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			statement = connection.createStatement();
						
		}
		catch(Exception e) {
			System.out.println("Failed to establish Connection");
		}
	}
	
	/**
	 * Function to remove connection i.e close all resources
	 */
	public void removeDatabaseConnection() {
		if(resultset != null) {
			try {
				resultset.close();
			}
			catch(SQLException e) {
				System.out.println("Error in closing resultset");
			}
		}
		if(statement != null) {
			try {
				statement.close();
			}
			catch(SQLException e) {
				System.out.println("Error in creating platform");
			}
		}
		if(connection != null) {
			try {
				connection.close();
			}
			catch(SQLException e) {
				System.out.println("Error in establishing platform");
			}
		}
	}
	
	/**
	 * Function to check user if user is present in the database or not
	 * @param emailId contains the email id entered by the user
	 * @param password contains the password entered by the user
	 * @return user object
	 */
	public User userLogin(String emailId, String password) {
		User user = new User();
		String query = "SELECT * from User_Database.User_Information where Email_Id = '"+ emailId +"' AND Password = '"+ password + "';";
		try {
			resultset = statement.executeQuery(query);
			if(resultset.next()) {
				user.setUserId(resultset.getInt(1));
				user.setUserName(resultset.getString(2));
				user.setEmailId(resultset.getString(3));
				user.setPassword(resultset.getString(4));
				user.setPhoneNumber((long) resultset.getDouble(5));
			}
			else {
				user = null;
				System.out.println("Invalid User");
			}
			
		} catch (SQLException e) {
			System.out.println("Error in creating platform");
		}
		return user;
	}
	
	/**
	 * Function to check if the user information is already present at the database
	 * @param email
	 * @return
	 */
	public User checkUserInDatabase(String email) {
		User user = new User();
		String query = "SELECT * from User_Database.User_Information where Email_Id = '" + email + "';";
		try {
			resultset = statement.executeQuery(query);
			if(resultset.next()) {
				user.setUserId(resultset.getInt(1));
				user.setUserName(resultset.getString(2));
				user.setEmailId(resultset.getString(3));
				user.setPassword(resultset.getString(4));
				user.setPhoneNumber((long) resultset.getDouble(5));
			}
			else {
				user = null;
			}
			
		} catch (SQLException e) {
			System.out.println("Error in creating platform");
		}
		return user;
	}
	
	/**
	 * Function for registration of new user
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param phoneNumber
	 */
	public void registerUser(int id, String name, String email, String password, long phoneNumber) {
		String query = "INSERT INTO User_Database.User_Information (User_Id, Name, Email_Id, Password, Phone_Number)" + 
				"values (" + id + ",'"  + name + "','"+ email + "','" +  password + "'," + phoneNumber + ");";
		try {
			statement.executeUpdate(query);
			System.out.println("User Registered");
		} catch (SQLException e) {
			System.out.println("Error in executing query");
			e.printStackTrace();
		}
	}
}
