package service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import helper.InputValidation;
import model.AddressBook;
import model.Contact;

public class AddressBookManager {
	
	private Set<AddressBook> addressBooks;
	public static final String DEFAULT_NAME = "default";
	
	public AddressBookManager(){
		addressBooks = new HashSet<>();
	}
	
	/*
	 * return null addressBook if not found
	 */
	public AddressBook getAddressBook(String name){
		Optional<AddressBook> addressBook = this.getAddressBooks().stream().filter(a-> a.getName().equals(InputValidation.validString(name)))
												.findFirst();
		return addressBook.orElse(null);
	}
	
	private AddressBook createAddressBook(String name){
		AddressBook addressBook = new AddressBook(name);
		getAddressBooks().add(addressBook);
		return addressBook;
	}

	public Set<AddressBook> getAddressBooks() {
		return addressBooks;
	}

	public void setAddressBooks(Set<AddressBook> addressBooks) {
		this.addressBooks = addressBooks;
	}
	
	public Set<Contact> mergeAllAddressBook(){
		return addressBooks.stream().flatMap(book -> book.getContacts().stream()).collect(Collectors.toSet());
	}

	public AddressBook addContact(String name, String phoneNumber) {
		return addContact(AddressBookManager.DEFAULT_NAME,name,phoneNumber);
	}

	public AddressBook addContact(String addressBookName, String name, String phoneNumber) {
		AddressBook addressBook = getOrCreateAddressBook(addressBookName);
		addressBook = createAndAddContactToAddressBook(addressBook, name, phoneNumber);
		return addressBook;
	}

	private AddressBook createAndAddContactToAddressBook(AddressBook addressBook, String name, String phoneNumber) {
		Contact contact = new Contact(name, phoneNumber);
		addressBook.addContact(contact);
		return addressBook;
	}

	private AddressBook getOrCreateAddressBook(String addressBookName) {
		AddressBook addressBook = getAddressBook(addressBookName);
		if(addressBook == null){
			addressBook = createAddressBook(addressBookName);
		}
		return addressBook;
	}

	public boolean removeContact(String contactName) {
		return removeContact(DEFAULT_NAME, contactName);
	}

	public boolean removeContact(String addressBookName, String contactName) {
		AddressBook addressBook = getAddressBook(addressBookName);
		
		if(addressBook == null){
			return false;
		}
		
		return addressBook.removeContact(contactName);
	}
}
