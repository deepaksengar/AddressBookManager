package service;

import model.AddressBook;

/*
 * interface for managing multiple address books
 */
public interface MultipleAddressBookService extends AddressBookService {
	
	AddressBook addContact(String addressBookName, String name, String phoneNumber);
	boolean removeContact(String addressBookName, String contactName);
	void printUniqueContacts();
	void printContacts(String addressBookName);
}
