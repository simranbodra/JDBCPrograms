package com.bridgelabz.utility;

import java.util.Scanner;

public class Utility {

	/*
	 * Static Variable is declared to create static object of Scanner class
	 */
	static Scanner scanner = new Scanner(System.in);
	
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
}
