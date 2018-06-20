package com.bridgelabz.batchprograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.bridgelabz.utility.Utility;

public class BatchUpdate {

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement statement =  null;
		
		String query1 = "INSERT INTO Student_Database.Student_Table (Student_Name, Department)values ('Prateek', 'Computer Science');";
		String query2 = "INSERT INTO Student_Database.Student_Table (Student_Name, Department)values ('Nawaz', 'Civil');";
		String query3 = "UPDATE Student_Database.Student_Table SET Department = 'Electrical' WHERE Student_Id = 103;";
		String query4 = "UPDATE Student_Database.Student_Table SET Department = 'Electrical' WHERE Student_Id = 101;";
		String query5 = "DELETE FROM Student_Database.Student_Table where Student_Id = 103;";
		String query6 = "INSERT INTO Student_Database.Student_Table (Student_Name, Department)values ('Prateek', 'Computer Science');";
		
		String driver = Utility.getProperty("Driver");
		String user = Utility.getProperty("User");
		String password = Utility.getProperty("Password");
		
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=" + user + "&password=" + password);
			statement = connection.createStatement();
			statement.addBatch(query1);
			statement.addBatch(query2);
			statement.addBatch(query3);
			statement.addBatch(query4);
			statement.addBatch(query5);
			statement.addBatch(query6);
			int[] array = statement.executeBatch();
			for(int x:array) {
				System.out.println(x);
			}
			System.out.println(array.length);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
