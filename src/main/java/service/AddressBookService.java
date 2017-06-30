package service;

import model.AddressBook;
import model.Contact;

/*
 * interface for managing single address book
 */
public interface AddressBookService {
	AddressBook addContact(String name, String phoneNumber);
	boolean removeContact(String contactName);
	void printContacts();
	Contact getContact(String contactName);
}
