package com.bridgelabz.addressbookupdate.addressbookserializer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.bridgelabz.addressbookupdate.model.Addressbook;
import com.bridgelabz.addressbookupdate.model.Person;
import com.bridgelabz.addressbookupdate.model.Status;
import com.bridgelabz.addressbookupdate.utility.Utility;

public class JsonSerializer implements AddressbookSerializer{

	@Override
	public List<String> readAddressbookList() {
		String fileName = Utility.getProperty("AddressbookList");
		JSONParser parser = new JSONParser();
		List<String> addressBookList = new LinkedList<>();
		try {
			
			Object obj = parser.parse(new FileReader(fileName));
			JSONArray personList = (JSONArray) obj;
			for(Object object : personList) {
				String addressBookName = object.toString();
				addressBookList.add(addressBookName);
			}
		}
		catch(Exception e) {
			System.out.println("Error in loading address book list");
		}
		return addressBookList;
	}

	@Override
	public void writeAddressbookList(List<String> listOfAddressbook) {
		String fileName = Utility.getProperty("AddressbookList");
		ObjectMapper mapper = new ObjectMapper();
		try {
		mapper.writeValue(new File(fileName), listOfAddressbook);
		System.out.println("operation successful....");
		}
		catch(IOException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}		
	}

	@Override
	public Addressbook readAddressbook(String addressbookName) {
		String fileName = Utility.getProperty("Location") + addressbookName + ".json";
		JSONParser parser = new JSONParser();
		Addressbook addressbook = new Addressbook();
		List<Person> personLinkedList = new LinkedList<>();
		try {
			
			Object obj = parser.parse(new FileReader(fileName));
			JSONObject addressBookDetails = (JSONObject) obj;
			
			JSONArray personList = (JSONArray) addressBookDetails.get("addressBook");
			for(Object object : personList) {
				JSONObject person = (JSONObject) object;
				String firstName = (String)person.get("firstName");
				String lastName = (String)person.get("lastName");
				String address = (String)person.get("address");
				String city = (String)person.get("city");
				String state = (String)person.get("state");
				String zipCode = (String)person.get("zipCode");
				String phoneNumber = (String)person.get("phoneNumber");
				Person personData = new Person(firstName, lastName, address, city, state, zipCode, phoneNumber);
				personLinkedList.add(personData);
			}
			
			String addressBookName = (String)addressBookDetails.get("addressBookName");
			long count = (long)addressBookDetails.get("count");
			addressbook.setAddressBook(personLinkedList);
			addressbook.setAddressBookName(addressBookName);
			addressbook.setCount(count);
		}
		catch(Exception e) {
			System.out.println("Error in getting addrress book information");
			//e.printStackTrace();
		}
		return addressbook;
	}

	@Override
	public void writeAddressbook(String addressbookName, Addressbook addressbook) {
		String location = Utility.getProperty("Location");
		String fileName = location + addressbookName + ".json";
		ObjectMapper mapper = new ObjectMapper();
		List<Person> listOfPerson = new LinkedList<>();
		List<Person> newListOfPerson = new LinkedList<>();
		Status enumObj = Status.valueOf("deleted".toUpperCase());
		listOfPerson = addressbook.getAddressBook();
		for(int i=0; i<listOfPerson.size(); i++) {
			if(!(listOfPerson.get(i).getStatus() == enumObj)) {
				listOfPerson.get(i).setStatus(Status.NOCHANGE);
				newListOfPerson.add(listOfPerson.get(i));
			}
		}
		addressbook.setAddressBook(newListOfPerson);
		try {
		mapper.writeValue(new File(fileName), addressbook);
		System.out.println("operation successful....");
		}
		catch(IOException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
	}

	@Override
	public void createNewAddressbook(String addressbookName, List<String> listOfAddressbook) {
		 boolean flag = false;
		 String location = Utility.getProperty("Location");

		File stockFile = new File(location + addressbookName + ".json");

		try {
		    flag = stockFile.createNewFile();
		} catch (IOException ioe) {
		     System.out.println("Error while Creating Addressbook" + ioe);
		}
		writeAddressbookList(listOfAddressbook);
		System.out.println("Addressbook created ");
	}
	
}
