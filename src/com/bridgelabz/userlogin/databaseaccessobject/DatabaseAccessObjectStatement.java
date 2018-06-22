package com.bridgelabz.userlogin.databaseaccessobject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bridgelabz.userlogin.model.User;

public class DatabaseAccessObjectStatement implements DatabaseAccessObject{
	
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
		Statement statement = null;
		ResultSet resultset = null;
		User user = new User();
		String query = "SELECT * from User_Database.User_Information where Email_Id = '"+ emailId +"' AND Password = '"+ password + "';";
		try {
			statement = connection.createStatement();
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
		finally {
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
		}
		return user;
	}
	
	/**
	 * Function to check if the user information is already present at the database
	 * @param email
	 * @return
	 */
	@Override
	public User checkUserInDatabase(String email, Connection connection) {
		Statement statement = null;
		ResultSet resultset = null;
		User user = new User();
		String query = "SELECT * from User_Database.User_Information where Email_Id = '" + email + "';";
		try {
			statement = connection.createStatement();
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
		finally {
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
	@Override
	public void registerUser(User user, Connection connection) {
		Statement statement = null;
		String query = "INSERT INTO User_Database.User_Information (User_Id, Name, Email_Id, Password, Phone_Number)" + 
				"values (" + user.getUserId() + ",'"  + user.getUserName() + "','"+ user.getEmailId() + "','" +  user.getPassword() + "'," + user.getPhoneNumber() + ");";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("User Registered");
		} catch (SQLException e) {
			System.out.println("Error in executing query");
			e.printStackTrace();
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
