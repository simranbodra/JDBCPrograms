package com.bridgelabz.addressbookupdate.model;

public enum Status {

	NOCHANGE, ADDED, UPDATED, DELETED;
	
	public String toString(){
        switch(this){
        case NOCHANGE :
            return "NOCHANGE";
        case ADDED :
            return "ADDED";
        case UPDATED :
            return "UPDATED";
        case DELETED :
        	return "DELETED";
        }
        return null;
    }
}
