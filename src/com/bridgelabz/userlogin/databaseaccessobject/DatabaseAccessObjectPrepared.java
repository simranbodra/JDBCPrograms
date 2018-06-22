package com.bridgelabz.userlogin.databaseaccessobject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bridgelabz.userlogin.model.User;

public class DatabaseAccessObjectPrepared implements DatabaseAccessObject{

	/**
	 * Function to establish connection with database and create platform
	 */
	@Override
	public Connection getDatabaseAccess() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			
		}
		catch(Exception e) {
			System.out.println("Failed to establish Connection");
		}
		return connection;
	}
	
	/**
	 * Function to check user if user is present in the database or not
	 * @param emailId contains the email id entered by the user
	 * @param password contains the password entered by the user
	 * @return user object
	 */
	@Override
	public User userLogin(String emailId, String password, Connection connection) {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		User user = new User();
		String query = "SELECT * from User_Database.User_Information where Email_Id = ? AND Password = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, emailId);
			statement.setString(2, password);
			resultset = statement.executeQuery();
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
		finally {
			if(resultset != null) {
				try {
					resultset.close();
				}
				catch(SQLException e) {
					System.out.println("Error in result set");
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
		}
		return user;
	}
	
	/**
	 * Function to check if the user is present in the database 
	 * @param emailId contains the email entered by the user
	 * @return user object if present else return null
	 */
	@Override
	public User checkUserInDatabase(String emailId, Connection connection) {
		PreparedStatement statement = null;
		ResultSet resultset = null;
		User user = new User();
		String query = "SELECT * from User_Database.User_Information where Email_Id = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, emailId);
			resultset = statement.executeQuery();
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
		finally {
			if(resultset != null) {
				try {
					resultset.close();
				}
				catch(SQLException e) {
					System.out.println("Error in result set");
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
		}
		return user;
	}
	
	/**
	 * Function to insert new row in the database
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param phoneNumber
	 */
	@Override
	public void registerUser(User user, Connection connection) {
		PreparedStatement statement = null;
		String query = "INSERT INTO User_Database.User_Information (User_Id, Name, Email_Id, Password, Phone_Number)\n" + 
				"values (?, ?, ?, ?, ?);";
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, user.getUserId());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getEmailId());
			statement.setString(4, user.getPassword());
			statement.setLong(5, user.getPhoneNumber());
			statement.executeUpdate();
			System.out.println("User registered");
		}
		catch(SQLException e) {
			System.out.println("Error in registering new user");
		}
		finally {
			if(statement != null) {
				try {
					statement.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
		}
	}
}
