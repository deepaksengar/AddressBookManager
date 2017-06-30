package service;

import model.AddressBook;
import model.Contact;

public class AddressBookServiceImpl implements AddressBookService {
	
	AddressBookManager addressBookManager;
	
	public AddressBookServiceImpl(){
		this.addressBookManager = new AddressBookManager();
	}
	
	@Override
	public boolean removeContact(String contactName){
		return addressBookManager.removeContact(contactName);
	}
	
	@Override
	public AddressBook addContact(String name, String phoneNumber){
		AddressBook addressBook = null;
		try{
			addressBook = addressBookManager.addContact(name, phoneNumber);
		} catch (RuntimeException ex){
			System.out.println("Contact not added. Error Message: " + ex.getMessage() );
		}
		return addressBook;
	}
	
	@Override
	public Contact getContact(String name){
		Contact contact = null;
		try{
			contact = addressBookManager.getContact(name);
		} catch (RuntimeException ex){
			System.out.println("Error Message: " + ex.getMessage() );
		}
		return contact;
	}
	
	/*
	 * Print Contact Functions
	 * @see service.AddressBookService#printContacts()
	 */
	@Override
	public void printContacts(){
		AddressBook addressBook = this.addressBookManager.getAddressBook(AddressBookManager.DEFAULT_NAME);
		if(addressBook == null){
			System.out.println("AddressBook doesn't exist.");
			return;
		}
		
		printAddressBookContacts(addressBook);
	}
	
	void printAddressBookContacts(AddressBook addressBook) {
		if(addressBook.getContacts().size() == 0){
			System.out.println("No contacts found.");
			return;
		}
		System.out.println("Contacts from AddressBook: " + addressBook.getName());
		addressBook.getContacts().forEach(contact -> {
									System.out.println(contact);
									});
	}

}
