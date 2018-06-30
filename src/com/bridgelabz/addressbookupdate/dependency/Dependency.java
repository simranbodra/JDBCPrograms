package com.bridgelabz.addressbookupdate.dependency;

import com.bridgelabz.addressbookupdate.addressbookserializer.AddressbookSerializer;
import com.bridgelabz.addressbookupdate.addressbookserializer.JdbcSerializer;
import com.bridgelabz.addressbookupdate.addressbookserializer.JsonSerializer;
import com.bridgelabz.addressbookupdate.utility.Utility;

public class Dependency {
	public static AddressbookSerializer getSerializationObject(String type) {
		while(true) {
			if(type.equalsIgnoreCase("jdbc")) {
				JdbcSerializer serializer = new JdbcSerializer();
				return serializer;
			}
			else {
				if(type.equalsIgnoreCase("json")) {
					JsonSerializer serializer = new JsonSerializer();
					return serializer;
				}
				else {
					System.out.println("wrong input");
					System.out.println("Enter json or jdbc ");
					type = Utility.stringInput();
				}
			}
		}
	}
}
