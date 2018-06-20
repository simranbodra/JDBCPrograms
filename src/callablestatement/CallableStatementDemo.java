package callablestatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class CallableStatementDemo {

	public static void main(String[] args) {
		
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultset = null;
		
		String query = "call User_Database.count_employees()";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			statement = connection.prepareCall(query);
			resultset = statement.executeQuery();
			if(resultset.next()) {
				System.out.println("Total Employees:- " + resultset.getInt(1));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(resultset != null) {
				try {
					resultset.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
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
