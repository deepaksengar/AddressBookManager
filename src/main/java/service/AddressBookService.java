package service;

import model.AddressBook;

/*
 * interface for managing single address book
 */
public interface AddressBookService {
	AddressBook addContact(String name, String phoneNumber);
	boolean removeContact(String contactName);
	void printContacts();
}
