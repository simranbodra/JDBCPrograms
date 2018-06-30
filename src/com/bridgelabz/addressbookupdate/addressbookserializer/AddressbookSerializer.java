package com.bridgelabz.addressbookupdate.addressbookserializer;

import java.util.List;

import com.bridgelabz.addressbookupdate.model.Addressbook;

public interface AddressbookSerializer {
	
	public List<String> readAddressbookList();
	public void writeAddressbookList(List<String> listOfAddressbook);
	public Addressbook readAddressbook(String location);
	public void writeAddressbook(String addressbookName, Addressbook addressbook);
	public void createNewAddressbook(String addressbookName, List<String> listOfAddressbook);
	
}
