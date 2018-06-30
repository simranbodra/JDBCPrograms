package com.bridgelabz.addressbookupdate.addressbookserializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import com.bridgelabz.addressbookupdate.model.Addressbook;
import com.bridgelabz.addressbookupdate.model.Person;
import com.bridgelabz.addressbookupdate.model.Status;
import com.bridgelabz.addressbookupdate.utility.Utility;

public class JdbcSerializer implements AddressbookSerializer{
	
public Connection getConnection() {
		
		Connection connection = null;
		String url = Utility.getProperty("Driver");
		String user = Utility.getProperty("User");
		String password = Utility.getProperty("Password");
		try {
			Class.forName(url);
			System.out.println("Driver class registered and loaded");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=" + user + "&password=" + password);
			System.out.println("Connection established with Database Server");
		}
		catch(Exception e) {
			System.out.println("Connection not established");
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public List<String> readAddressbookList() {
		
		PreparedStatement statement = null;
		ResultSet resultset = null;
		

		Connection connection = getConnection();
		
		List<String> addressbookList = new LinkedList<>();
		String url = Utility.getProperty("Driver");
		String user = Utility.getProperty("User");
		@SuppressWarnings("unused")
		String password = Utility.getProperty("Password");

		try {
			String query = "SELECT * from Addressbook.Addressbook_List";
			
			statement = connection.prepareStatement(query);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				addressbookList.add(resultset.getString(2));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error in loading addressbook list");
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
			if(connection != null) {
				try {
					connection.close();
				}
				catch(SQLException e) {
					System.out.println("Error in establishing platform");
				}
			}
		}
		return addressbookList;
	}

	@Override
	public void writeAddressbookList(List<String> listOfAddressbook) {
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		
		Connection connection = getConnection();
		
		String url = Utility.getProperty("Driver");
		String user = Utility.getProperty("User");
		String password = Utility.getProperty("Password");
		
		try {

			String query1 = "TRUNCATE TABLE Addressbook.Addressbook_List;";
			String query2 = "INSERT INTO Addressbook.Addressbook_List values(?, ?);";
			
			statement1 = connection.prepareStatement(query1);
			statement2 = connection.prepareStatement(query2);
			statement1.executeUpdate();
			for(int i=0; i<listOfAddressbook.size(); i++) {
				String addressbookName = listOfAddressbook.get(i);
				statement2.setInt(1, 0);
				statement2.setString(2, addressbookName);
				statement2.executeUpdate();
			}
		}
		catch(Exception e) {
			System.out.println("Error in updating list");
			e.printStackTrace();
		}
		finally {
			if(statement2 != null) {
				try {
					statement2.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
			if(statement1 != null) {
				try {
					statement1.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
		}
	}

	@Override
	public Addressbook readAddressbook(String tableName) {
		PreparedStatement statement1 = null;
		ResultSet resultset1 = null;
		
		Connection connection = getConnection();
		
		Addressbook addressbook = new Addressbook();
		List<Person> personList = new LinkedList<>();
		String url = Utility.getProperty("Driver");
		String user = Utility.getProperty("User");
		String password = Utility.getProperty("Password");
		
		try {
			
			String query1 = "SELECT * from Addressbook." + tableName + ";";
			
			statement1 = connection.prepareStatement(query1);
			resultset1 = statement1.executeQuery();
			while(resultset1.next()) {
				Person person = new Person();
				person.setFirstName(resultset1.getString(1));
				person.setLastName(resultset1.getString(2));
				person.setAddress(resultset1.getString(3));
				person.setCity(resultset1.getString(4));
				person.setZipCode(resultset1.getString(5));
				person.setPhoneNumber(resultset1.getString(6));
				person.setStatus(Status.NOCHANGE);
				personList.add(person);
			}
			addressbook.setAddressBook(personList);
			addressbook.setAddressBookName(tableName);
			addressbook.setCount(personList.size());
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error in loading addressbook");
		}
		finally {
			if(resultset1 != null) {
				try {
					resultset1.close();
				}
				catch(SQLException e) {
					System.out.println("Error in result set");
				}
			}
			if(statement1 != null) {
				try {
					statement1.close();
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
		return addressbook;
	}

	@Override
	public void writeAddressbook(String addressbookName, Addressbook addressbook) {
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		
		Connection connection = getConnection();
		
		String url = Utility.getProperty("Driver");
		String user = Utility.getProperty("User");
		String password = Utility.getProperty("Password");
		List<Person> listOfPerson = new LinkedList<>();
		listOfPerson = addressbook.getAddressBook();
		
		try {

			String query1 = "INSERT INTO Addressbook." + addressbookName + " values(?, ?, ?, ?, ?, ?, ?);";
			String query2 = "UPDATE Addressbook." + addressbookName + " SET Address = ?,City = ?, State = ?, Zipcode = ?, PhoneNumber = ? WHERE FirstName = ? AND LastName = ?";
			String query3 = "DELETE FROM Addressbook." + addressbookName + " WHERE FirstName = ? AND LastName = ?";
			
			statement1 = connection.prepareStatement(query1);
			statement2 = connection.prepareStatement(query2);
			statement3 = connection.prepareStatement(query3);
			for(int i=0; i<listOfPerson.size(); i++) {
				Status status = listOfPerson.get(i).getStatus();
				System.out.println(status);
				switch(listOfPerson.get(i).getStatus()) {
				case ADDED:
					String firstName = listOfPerson.get(i).getFirstName();
					String lastName = listOfPerson.get(i).getLastName();
					String address = listOfPerson.get(i).getAddress();
					String city = listOfPerson.get(i).getCity();
					String state = listOfPerson.get(i).getState();
					String zipcode = listOfPerson.get(i).getZipCode();
					String phoneNumber = listOfPerson.get(i).getPhoneNumber();
					statement1.setString(1, firstName);
					statement1.setString(2, lastName);
					statement1.setString(3, address);
					statement1.setString(4, city);
					statement1.setString(5, state);
					statement1.setString(6, zipcode);
					statement1.setString(7, phoneNumber);
					statement1.executeUpdate();
					break;
					
				case UPDATED:
					String updatedFirstName = listOfPerson.get(i).getFirstName();
					String updatedLastName = listOfPerson.get(i).getLastName();
					String updatedAddress = listOfPerson.get(i).getAddress();
					String updatedCity = listOfPerson.get(i).getCity();
					String updatedState = listOfPerson.get(i).getState();
					String updatedZipcode = listOfPerson.get(i).getZipCode();
					String updatedPhoneNumber = listOfPerson.get(i).getPhoneNumber();
					statement2.setString(1, updatedAddress);
					statement2.setString(2, updatedCity);
					statement2.setString(3, updatedState);
					statement2.setString(4, updatedZipcode);
					statement2.setString(5, updatedPhoneNumber);
					statement2.setString(6, updatedFirstName);
					statement2.setString(7, updatedLastName);
					statement2.executeUpdate();
					break;
					
				case DELETED:
					String deletedFirstName = listOfPerson.get(i).getFirstName();
					String deletedLastName = listOfPerson.get(i).getLastName();
					statement3.setString(1, deletedFirstName);
					statement3.setString(2, deletedLastName);
					statement3.executeUpdate();
					break;
					
				default:
					System.out.println("running default");
					break;
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error in updating list");
			e.printStackTrace();
		}
		finally {
			if(statement2 != null) {
				try {
					statement2.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
			if(statement1 != null) {
				try {
					statement1.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
		}
	}
	
	@Override
	public void createNewAddressbook(String addressbookName, List<String> listOfAddressbook) {
		PreparedStatement statement0 = null;
		PreparedStatement statement1 = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		ResultSet resultset = null;
		
		Connection connection = getConnection();
		
		String url = Utility.getProperty("Driver");
		String user = Utility.getProperty("User");
		String password = Utility.getProperty("Password");
		
		List<String> addressbookNames = new LinkedList<>();
		
		try {
			
			String query0 = "Use Addressbook;";
			statement0 = connection.prepareStatement(query0);
			statement0.executeQuery();
			
			String query1 = "show tables;";
			statement1 = connection.prepareStatement(query1);
			resultset = statement1.executeQuery();
			while(resultset.next()) {
				addressbookNames.add(resultset.getString(1));
			}
			
			if(addressbookNames.contains(addressbookName)) {
				String query2 = "TRUNCATE TABLE Addressbook." + addressbookName;
				statement2 = connection.prepareStatement(query2);
				statement2.executeUpdate();
				
			}
			else {
				String query3 = "CREATE TABLE Addressbook." + addressbookName + "(" + 
						"	 FirstName varchar(255)," +
						"    LastName varchar(255)," + 
						"    Address varchar(255)," + 
						"    City varchar(255), " +
						"	 State varchar(255)," +
						"	 Zipcode varchar(255)," +
						"	 PhoneNumber varchar(255))";
				
				statement3 = connection.prepareStatement(query3);
				statement3.executeUpdate();
			}
			
		}
		catch(Exception e) {
			System.out.println("Error in creating table");
			e.printStackTrace();
		}
		finally {
			if(resultset != null) {
				try {
					resultset.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
			if(statement0 != null) {
				try {
					statement0.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
			if(statement1 != null) {
				try {
					statement1.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
			if(statement2 != null) {
				try {
					statement2.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
			if(statement3 != null) {
				try {
					statement3.close();
				}
				catch(SQLException e) {
					System.out.println("Error in creating platform");
				}
			}
		}
	}
	
}
