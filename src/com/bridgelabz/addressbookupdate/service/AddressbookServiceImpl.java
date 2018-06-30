package com.bridgelabz.addressbookupdate.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.bridgelabz.addressbookupdate.addressbookserializer.AddressbookSerializer;
import com.bridgelabz.addressbookupdate.dependency.Dependency;
import com.bridgelabz.addressbookupdate.model.Addressbook;
import com.bridgelabz.addressbookupdate.model.Person;
import com.bridgelabz.addressbookupdate.model.Status;
import com.bridgelabz.addressbookupdate.utility.Utility;

public class AddressbookServiceImpl {

	AddressbookSerializer serializer;

	private List<String> listOfAddressbook = new LinkedList<>();
	int addressbookCount = 0;
	
	public AddressbookServiceImpl(String serializationType){
		this.serializer = Dependency.getSerializationObject(serializationType);
		
		this.listOfAddressbook = serializer.readAddressbookList();
	}
	
	public List<String> getListOfAddressbook() {
		return listOfAddressbook;
	}

	public void setListOfAddressbook(List<String> listOfAddressbook) {
		this.listOfAddressbook = listOfAddressbook;
	}
	
	public int getAddressbookCount() {
		return listOfAddressbook.size();
	}
	
	@Override
	public String toString() {
		Iterator<String> iterator = listOfAddressbook.iterator();
		int index = 1;
		while(iterator.hasNext()) {
			System.out.println(index + ". " + iterator.next());
			index++;
		}
		return "";
	}

	
	public void addToAddressBookList(String addressbookName) {
		listOfAddressbook.add(addressbookName);
		System.out.println("Added to list");
		serializer.createNewAddressbook(addressbookName, listOfAddressbook);
		
	}

	public Addressbook openAddressbook(int index) {
		String addressbookName = "";
		for(int i=0; i<listOfAddressbook.size(); i++) {
			if(i == index-1) {
				addressbookName = listOfAddressbook.get(i);
				break;
			}
		}
		Addressbook addressbook = serializer.readAddressbook(addressbookName);
		addressbook.setAddressBookName(addressbookName);
		return addressbook;
	}
	
	public void printAddressbook(List<Person> list) {
		List<Person> listOfPerson = new LinkedList<>();
		Status enumObj = Status.valueOf("deleted".toUpperCase());
		listOfPerson = list;
		for(int i=0; i<listOfPerson.size(); i++) {
			if(!(listOfPerson.get(i).getStatus() == enumObj)) {
				String firstName = listOfPerson.get(i).getFirstName();
				String lastName = listOfPerson.get(i).getLastName();
				System.out.println(i+1 + ". " + firstName + " "+ lastName);
			}
		}
	}
	
	public int searchPerson(Addressbook addressbook, String fullName){
		List<Person> listOfPerson = new LinkedList<>();
		Person person = new Person();
		int index = 0;
		listOfPerson = addressbook.getAddressBook();
		for(int i=0; i<listOfPerson.size(); i++){
			person = listOfPerson.get(i);
			if((person.getFirstName() + " " +person.getLastName()).equalsIgnoreCase(fullName)){
				index = i;
			}
		}
		return index;
	}
	
	public List<Person> addPersonToAddressbook(Addressbook addressbook, Person person) {
		List<Person> listOfPerson = new LinkedList<>();
		List<Person> newListOfPerson = new LinkedList<>();
		listOfPerson = addressbook.getAddressBook();
		newListOfPerson = listOfPerson;
		newListOfPerson.add(person);
		return newListOfPerson;
	}
	
	public List<Person> deletePersonFromAddressbook(String fullName, Addressbook addressbook){
		List<Person> listOfPerson = new LinkedList<>();
		List<Person> newListOfPerson = new LinkedList<>();
		Person person = new Person();
		listOfPerson = addressbook.getAddressBook();
		newListOfPerson = listOfPerson;
		int index = searchPerson(addressbook, fullName);
		for(int i=0; i<listOfPerson.size(); i++){
			if(index == i){
				person = newListOfPerson.get(i);
				newListOfPerson.remove(i);
				break;
			}
		}
		person.setStatus(Status.DELETED);
		newListOfPerson.add(person);
		return newListOfPerson;
	}
	
	public List<Person> updatePersonDetails(String fullName, Addressbook addressbook, Person newPerson){
		List<Person> listOfPerson = new LinkedList<>();
		List<Person> newListOfPerson = new LinkedList<>();
		listOfPerson = addressbook.getAddressBook();
		newListOfPerson = listOfPerson;
		int index = searchPerson(addressbook, fullName);
		for(int i=0; i<listOfPerson.size(); i++){
			if(index == i){
				Person person = newListOfPerson.get(i);
				newPerson.setFirstName(person.getFirstName());
				newPerson.setLastName(person.getLastName());
				newListOfPerson.remove(i);
				break;
			}
		}
		newListOfPerson.add(newPerson);
		return newListOfPerson;
	}
	
	public Person getPersonDetailsFromAddressBook(Addressbook addressbook, String fullName){
		List<Person> listOfPerson = new LinkedList<>();
		listOfPerson = addressbook.getAddressBook();
		Person person = new Person();
		int index = searchPerson(addressbook, fullName);
		for(int i=0; i<listOfPerson.size(); i++){
			if(i == index){
				person = listOfPerson.get(i);
			}
		}
		return person;
	}
	
	public List<String> sortAddressBookByName(Addressbook addressbook){
		List<Person> listOfPerson = new LinkedList<>();
		listOfPerson = addressbook.getAddressBook();
		List<String> nameList = new LinkedList<>();
		for(int i=0; i<listOfPerson.size()-1; i++) {
			for(int j=0; j<listOfPerson.size()-1-i; j++) {
				if(listOfPerson.get(j).getFirstName().compareToIgnoreCase(listOfPerson.get(j+1).getFirstName()) > 0) {
					Person person = listOfPerson.get(j+1);
					listOfPerson.set(j+1,listOfPerson.get(j));
					listOfPerson.set(j, person);
				}
			}
		}
		for(int i=0; i<listOfPerson.size(); i++){
			nameList.add(listOfPerson.get(i).getFirstName()+ " "+listOfPerson.get(i).getLastName());
		}
		return nameList;
	}
	
	public List<Person> sortAddressBookByZipcode(Addressbook addressbook){
		List<Person> listOfPerson = new LinkedList<>();
		listOfPerson = addressbook.getAddressBook();
		for(int i=0; i<listOfPerson.size()-1; i++) {
			for(int j=0; j<listOfPerson.size()-1-i; j++) {
				Long zipcode1 = Long.parseLong(listOfPerson.get(j).getZipCode());
				Long zipcode2 = Long.parseLong(listOfPerson.get(j+1).getZipCode());
				if(zipcode1.compareTo(zipcode2) > 0) {
					Person person = listOfPerson.get(j+1);
					listOfPerson.set(j+1,listOfPerson.get(j));
					listOfPerson.set(j, person);
				}
			}
		}
		return listOfPerson;
	}
	
	public List<String> deleteAddressBook(int optionForDelete){
		List<String> oldList = new LinkedList<>();
		List<String> newList = new LinkedList<>();
		oldList = listOfAddressbook;
		newList = oldList;
		for(int i=0; i<oldList.size(); i++){
			if(i == (optionForDelete - 1)){
				newList.remove(i);
				break;
			}
		}
		Utility.stringInput();
		System.out.println("Do you want to save? Y/N");
		String choice = Utility.stringInput();
		if(choice.equalsIgnoreCase("Y")){
			serializer.writeAddressbookList(newList);
			return newList;
		}
		else{
			return oldList;
		}
	}
	
	public void save(List<String> newAddressbookList, Addressbook newPersonList) {
		Utility.stringInput();
		System.out.println("Do you want to save? Y/N");
		String choice = Utility.stringInput();
		if(choice.equalsIgnoreCase("Y")){
			serializer.writeAddressbookList(newAddressbookList);
			serializer.writeAddressbook(newPersonList.getAddressBookName(), newPersonList);
		}
	}
}
