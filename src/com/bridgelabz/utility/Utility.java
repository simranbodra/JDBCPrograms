package com.bridgelabz.utility;

import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

public class Utility {


	/*
	 * Static Variable is declared to create static object of Scanner class
	 */
	static Scanner scanner = new Scanner(System.in);
	
	/****************************************************************************************
	 * Function to take String input from the user
	 ****************************************************************************************/
	public static String stringInput() {
		String n = scanner.nextLine();
		return n;
	}
	
	/*************************************************************************************
	 * Function to take valid integer input from the user
	 **************************************************************************************/
	public static int integerInput()
	{
		try
		{
			int number = scanner.nextInt();
			return number;
		}
		catch(Exception e)
		{
			scanner.nextLine();
			System.out.println("Invalid input, enter again");
			return integerInput();
		}
	}
	
	/*************************************************************************************
	 * Function to take valid long integer input from the user
	 **************************************************************************************/
	public static long longIntegerInput()
	{
		try
		{
			long number = scanner.nextLong();
			return number;
		}
		catch(Exception e)
		{
			scanner.nextLine();
			System.out.println("Invalid input, enter again");
			return integerInput();
		}
	}
	
	/**
	 * Function to get value for a particular key from the file
	 * @param key
	 * @return the value
	 */
	public static String getProperty(String key) {
		String value = null;
		Properties property = new Properties();
		try {
			FileReader reader = new FileReader("/home/administrator/javaprograms/batchprograms/File.properties");
			property.load(reader);
			value = property.getProperty(key);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
