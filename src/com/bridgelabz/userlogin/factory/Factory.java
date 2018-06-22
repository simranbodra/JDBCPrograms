package com.bridgelabz.userlogin.factory;

import com.bridgelabz.userlogin.databaseaccessobject.DatabaseAccessObject;
import com.bridgelabz.userlogin.databaseaccessobject.DatabaseAccessObjectPrepared;
import com.bridgelabz.userlogin.databaseaccessobject.DatabaseAccessObjectStatement;

public class Factory {

	public static DatabaseAccessObject getObject(String statementType) {
		if(statementType.equalsIgnoreCase("prepared")) {
			DatabaseAccessObject object = new DatabaseAccessObjectPrepared();
			return object;
		}
		else {
			if(statementType.equalsIgnoreCase("statement")) {
				DatabaseAccessObject object = new DatabaseAccessObjectStatement();
				return object;
			}
			else {
				System.out.println("not matched");
				return null;
			}
		}
	}
}
