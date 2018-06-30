package com.bridgelabz.addressbookupdate.controller;

import java.util.List;

import com.bridgelabz.addressbookupdate.model.Addressbook;
import com.bridgelabz.addressbookupdate.model.Person;
import com.bridgelabz.addressbookupdate.model.Status;
import com.bridgelabz.addressbookupdate.service.AddressbookService;
import com.bridgelabz.addressbookupdate.service.AddressbookServiceImpl;
import com.bridgelabz.addressbookupdate.utility.Utility;

public class AddressbookApplication {
public static void main(String[] args) {
		
		AddressbookServiceImpl service = new AddressbookServiceImpl(args[0]);
		System.out.println("Welcome to My AddressBook....");
		int choice = 0;
		while(choice < 4) {
			System.out.println("Menu:-");
			System.out.println("1. View list of Address Book");
			System.out.println("2. create a new Address Book");
			System.out.println("3. Exit");
			System.out.println("Enter your choice:-");
			choice = Utility.integerInput();
			Utility.stringInput();
			switch(choice) {
			case 1:
				if(service.getAddressbookCount() == 0) {
					System.out.println("No addressbok created");
				}
				else {
					System.out.println(service);
					System.out.println("Menu:-");
					System.out.println("1. Open Address Book");
					System.out.println("2. Delete Address Book");
					System.out.println("3. return");
					System.out.println("Enter choice:-");
					int optionForAddressbook = Utility.integerInput();
					switch(optionForAddressbook) {
					case 1:
						System.out.println("Enter number to open the Address Book:-");
						int option = Utility.integerInput();
						Addressbook addressbook = service.openAddressbook(option);
						while(option <= 6) {
							if(option == 7){
								break;
							}
							service.printAddressbook(addressbook.getAddressBook());
							System.out.println("1. Add person to the address book");
							System.out.println("2. Delete person from the address book");
							System.out.println("3. updtae person details");
							System.out.println("4. Get person's details");
							System.out.println("5. Sort by name");
							System.out.println("6. Sort by Zipcode");
							System.out.println("7. Return");
							option = Utility.integerInput();
							switch(option) {
							case 1: 
								Person person = new Person();
								Utility.stringInput();
								System.out.println("Person details to be filled");
								System.out.println("Enter First Name:-");
								person.setFirstName(Utility.stringInput());
								System.out.println("Enter Last Name:-");
								person.setLastName(Utility.stringInput());
								System.out.println("Enter Address:-");
								person.setAddress(Utility.stringInput());
								System.out.println("Enter City:-");
								person.setCity(Utility.stringInput());
								System.out.println("Enter State:-");
								person.setState(Utility.stringInput());
								System.out.println("Enter Zipcode:-");
								person.setZipCode(Utility.stringInput());
								System.out.println("Enter Phone Number:-");
								person.setPhoneNumber(Utility.stringInput());
								person.setStatus(Status.ADDED);
								addressbook.setAddressBook(service.addPersonToAddressbook(addressbook, person));
								break;
						
							case 2:
								Utility.stringInput();
								System.out.println("Enter Full Name:-");
								String fullName = Utility.stringInput();
								addressbook.setAddressBook(service.deletePersonFromAddressbook(fullName, addressbook));
								break;
							
							case 3:
								Utility.stringInput();
								System.out.println("Enter Full Name:-");
								String name = Utility.stringInput();
								Person personDetails = new Person();
								System.out.println("Enter Address:-");
								personDetails.setAddress(Utility.stringInput());
								System.out.println("Enter City:-");
								personDetails.setCity(Utility.stringInput());
								System.out.println("Enter State:-");
								personDetails.setState(Utility.stringInput());
								System.out.println("Enter Zipcode:-");
								personDetails.setZipCode(Utility.stringInput());
								System.out.println("Enter Phone number:-");
								personDetails.setPhoneNumber(Utility.stringInput());
								personDetails.setStatus(Status.UPDATED);
								addressbook.setAddressBook(service.updatePersonDetails(name, addressbook, personDetails));
								break;
							
							case 4:
								Utility.stringInput();
								System.out.println("Enter Full Name:-");
								String personName = Utility.stringInput();
								Person details = service.getPersonDetailsFromAddressBook(addressbook, personName);
								System.out.println("Name:- " + details.getFirstName() + " " + details.getLastName());
								System.out.println("Address:- " + details.getAddress());
								System.out.println("City:- " + details.getCity());
								System.out.println("State:- " + details.getState());
								System.out.println("Zipcode:-" + details.getZipCode());
								System.out.println("Phone Number:-" + details.getPhoneNumber());
								break;
							
							case 5:
								List<String> listOfName = service.sortAddressBookByName(addressbook);
								for(int i=0; i<listOfName.size(); i++){
									System.out.println(i+1 +". "+ listOfName.get(i));
								}
								break;
								
							case 6:
								List<Person> listOfPerson = service.sortAddressBookByZipcode(addressbook);
								for(int i=0; i<listOfPerson.size(); i++){
									System.out.println(i+1 +". "+ listOfPerson.get(i).getFirstName()+ " " +listOfPerson.get(i).getLastName());
									System.out.println("Zipcode:- " +listOfPerson.get(i).getZipCode());
								}
								break;
								
							case 7:
								service.save(service.getListOfAddressbook(), addressbook);
								System.out.println("Changes saved...");
								break;
								
							default:
								System.out.println("wrong choice");
								option = Utility.integerInput();
							}
						}
						break;
												
					case 2:
						System.out.println("Enter numbre to delete the Address Book:-");
						int optionForDelete = Utility.integerInput();
						service.deleteAddressBook(optionForDelete);
						break;
						
					case 3:
						break;
					}	
				}
				break;
			case 2:
				System.out.println("Enter name for the addressbook:-");
				String addressbookName = Utility.stringInput();
				while(service.getListOfAddressbook().contains(addressbookName)) {
					System.out.println("Address Book with this name already exits");
					System.out.println("Enter new name:-");
					addressbookName = Utility.stringInput();
				}
				service.addToAddressBookList(addressbookName);
				break;
				
			case 3:
				System.exit(0);
				break;
				
			default:
				System.out.println("Wrong choice.. Enter again");
				choice = Utility.integerInput();
			}
		}
	}
}
