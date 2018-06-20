package com.bridgelabz.transactionprograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Savepoint;

import com.bridgelabz.utility.Utility;

public class SavepointDemo {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		Savepoint save = null;
		
		String query1 = "INSERT INTO User_Database.Employee (Employee_Name,Employee_Salary)value('Shruti',55000);";
		String query2 = "INSERT INTO User_Database.Employee (Employee_Name,Employee_Salary)value('Aditya',45000);";
		String query3 = "INSERT INTO User_Database.Employee (Employee_Name,Employee_Salary)value('Prateek',5000);";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			statement1 = connection.prepareStatement(query1);
			statement1.executeUpdate();
			connection.setAutoCommit(false);
			statement2 = connection.prepareStatement(query2);
			statement2.executeUpdate();
			save = connection.setSavepoint();
			statement3 = connection.prepareStatement(query3);
			statement3.executeUpdate();
			System.out.println("1. commit ");
			System.out.println("2. rollback ");
			System.out.println("Enter choice");
			int choice = Utility.integerInput();
			if(choice == 1) {
				connection.commit();
				System.out.println("Data inserted");
			}
			else {
				connection.rollback(save);
				System.out.println("rollbacked");
				connection.commit();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(statement3 != null) {
				try {
					statement3.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(statement2 != null) {
				try {
					statement2.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(statement1 != null) {
				try {
					statement1.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
