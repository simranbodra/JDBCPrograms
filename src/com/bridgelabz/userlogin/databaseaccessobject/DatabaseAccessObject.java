package com.bridgelabz.userlogin.databaseaccessobject;

import java.sql.Connection;

import com.bridgelabz.userlogin.model.User;

public interface DatabaseAccessObject {

	public Connection getDatabaseAccess();
	public User userLogin(String emailId, String password, Connection connection);
	public User checkUserInDatabase(String emailId, Connection connection);
	public void registerUser(User user, Connection connection);
}
