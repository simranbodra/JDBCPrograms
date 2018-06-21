package com.bridgelabz.service;

import com.bridgelabz.model.User;

public interface DatabaseAccessObject {

	public void getDatabaseAccess();
	public void removeDatabaseConnection();
	public User userLogin(String emailId, String password);
	public User checkUserInDatabase(String email);
	public void registerUser(int id, String name, String email, String password, long phoneNumber);
	
}
