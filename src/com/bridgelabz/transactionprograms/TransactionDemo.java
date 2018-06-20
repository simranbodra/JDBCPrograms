package com.bridgelabz.transactionprograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.bridgelabz.utility.Utility;

public class TransactionDemo {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		
		String query1 = "INSERT INTO User_Database.Employee (Employee_Name,Employee_Salary)value('Shruti',55000);";
		String query2 = "INSERT INTO User_Database.Employee (Employee_Name,Employee_Salary)value('Aditya',45000);";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			statement1 = connection.prepareStatement(query1);
			statement1.executeUpdate();
			connection.setAutoCommit(false);
			statement2 = connection.prepareStatement(query2);
			statement2.executeUpdate();
			System.out.println("1. commit ");
			System.out.println("2. roolback ");
			System.out.println("Enter choice");
			int choice = Utility.integerInput();
			if(choice == 1) {
				connection.commit();
				System.out.println("Data inserted");
			}
			else {
				connection.rollback();
				System.out.println("rollbacked");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
