package service;

import java.util.Set;

import helper.InputValidation;
import model.AddressBook;
import model.Contact;

public class MultipleAddressBookServiceImpl extends AddressBookServiceImpl implements MultipleAddressBookService {
	
	
	public MultipleAddressBookServiceImpl(){
		super();
	}
	
	@Override
	public Contact getContact(String addressBookName, String contactName){
		Contact contact = null;
		try{
			contact = addressBookManager.getContact( addressBookName, contactName);
		} catch (Exception ex){
			System.out.println("Contact not found. Error Message: " + ex.getMessage());
		}
		return contact;
	}

	@Override
	public boolean removeContact(String addressBookName, String contactName){
		return addressBookManager.removeContact(addressBookName, contactName);
	}
	
	@Override
	public AddressBook addContact(String addressBookName, String name, String phoneNumber) {
		return addressBookManager.addContact(addressBookName, name, phoneNumber);
	}
	
	/*
	 * Print Contacts from specific AddressBook.
	 * User needs to provide name of AddressBook
	 * @param: addressBookName
	 */
	@Override
	public void printContacts(String addressBookName){
		
		String validAddressBookName;
		try{
			validAddressBookName = InputValidation.validString(addressBookName);
		} catch (IllegalArgumentException ex){
			System.out.println("AddressBookName is not valid.");
			return;
		}
		
		AddressBook addressBook = addressBookManager.getAddressBook(validAddressBookName);
		if(addressBook == null){
			System.out.println("AddressBook: " + addressBookName + " doesn't exist.");
			return;
		}
		
		printAddressBookContacts(addressBook);
	}

	@Override
	public void printUniqueContacts(){
		Set<Contact> mergedContacts = addressBookManager.mergeAllAddressBook();
		mergedContacts.stream().sorted().forEach( c -> {
			System.out.println(c);
		});
	}
	
	/*
	 * Print all address books with contacts
	 */
	public void printContactsFromAllAddressBooks(){
		this.addressBookManager.getAddressBooks().stream()
		.forEach(addressBook -> {
			printAddressBookContacts(addressBook);
		});
	}
}
